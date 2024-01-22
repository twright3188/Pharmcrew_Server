package com.bumdori.spring.apitest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bumdori.spring.annotation.*;
import com.bumdori.util.ClassUtils;
import com.bumdori.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

public abstract class AbsApiMgr {
	
	private Map<String, ControllerInfo> controllers = new TreeMap<String, ControllerInfo>();
	
	private Logger logger = LoggerFactory.getLogger(AbsApiMgr.class);

	public AbsApiMgr(String basePackage) {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
		for (BeanDefinition beanDef : provider.findCandidateComponents(basePackage)) {
			String className = beanDef.getBeanClassName();
			addController(className);
		}
	}
	
	public Map<String, ControllerInfo> getControllers() {
		return controllers;
	}
	
	private void addController(String className) {
		System.out.println("addController-className: " + className);
		try {
			Class<?> clazz = Class.forName(className);
			
			Controller controllerAnnotation = clazz.getAnnotation(Controller.class);
			ControllerInfo controller = new ControllerInfo();
			controller.setClassName(clazz.getSimpleName());
			if (controllerAnnotation == null || StringUtils.isEmpty(controllerAnnotation.value())) {
				controller.setName(clazz.getSimpleName());
				controller.setError("Controller annotation에 value가 없음");
			} else {
				controller.setName(controllerAnnotation.value());
				
				Method[] methods = clazz.getMethods();
				for (Method method: methods) {
					RequestMapping requestMappingAnnotation = method.getAnnotation(RequestMapping.class);
					if (requestMappingAnnotation != null) {
						ResponseBody responseBodyAnnotation = method.getAnnotation(ResponseBody.class);
						if (responseBodyAnnotation != null) {	// api만...
							ApiInfo api = new ApiInfo();
							api.setFunction(method);
							api.setResponse(method.getReturnType());
							
							if (StringUtils.isEmpty(requestMappingAnnotation.name())) {
								api.setName(method.getName());
								api.setError("RequestMapping annotation에 name이 없음");
							} else if (requestMappingAnnotation.value() == null || StringUtils.isEmpty(requestMappingAnnotation.value()[0])) {
								api.setName(requestMappingAnnotation.name());
								api.setError("RequestMapping annotation에 value가 없음");
							} else if (requestMappingAnnotation.method() == null) {
								api.setName(requestMappingAnnotation.name());
								api.setError("RequestMapping annotation에 method가 없음");
							} else {
								api.setName(requestMappingAnnotation.name());
								api.setUrl(requestMappingAnnotation.value()[0]);
								api.setMethod(requestMappingAnnotation.method()[0]);
								
								setParameter(api, method);
								setSession(api, method);
								setDescription(api, method);
								setHistory(api, method);
								setValidation(api, method);
								int depth = setResult(api.getResults(), method.getReturnType(), 0);
								api.setResultDepth(depth);
							}
							
//							controller.getApis().put(method.getName(), api);
							controller.getApis().put(api.getName(), api);
							//logger.info("api: {}", api);
						}
					}	// for
				}
				
				// sort
//				Map<String, Api> apis = controller.getApis();
//				NameAcsComparator comparator = new NameAcsComparator(apis);
//				Map<String, Api> sortedApis = new TreeMap<String, Api>(comparator);
//				controller.setApis(sortedApis);
			}
//			controllers.put(clazz.getSimpleName(), controller);
			controllers.put(controller.getName(), controller);
//			logger.info("controller: {}", controller);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void setParameter(ApiInfo api, Method method) {
		Parameter[] parameters = method.getParameters();
		for (Parameter parameter: parameters) {
			PathVariable pathVariableAnnotation = parameter.getAnnotation(PathVariable.class);
			RequestParam requestParamAnnotation = parameter.getAnnotation(RequestParam.class);
			
			ParameterInfo parameterInfo = new ParameterInfo();
			Description descriptionAnnotation = parameter.getAnnotation(Description.class);
			if (descriptionAnnotation != null) {
				parameterInfo.setDescription(descriptionAnnotation.value());
			}
			
			if (pathVariableAnnotation != null) {
				if (StringUtils.isEmpty(pathVariableAnnotation.value())) {
					parameterInfo.setName(parameter.getName());
					parameterInfo.setError("PathVariable annotation에 value가 없음");
				} else {
					parameterInfo.setName(pathVariableAnnotation.value());
					parameterInfo.setType(getType(parameter.getType()));//PARAMETER_TYPE.STRING);
				}
				api.getPathParameters().add(parameterInfo);
				continue;
			} else if (requestParamAnnotation != null) {
				if (StringUtils.isEmpty(requestParamAnnotation.value())) {
					parameterInfo.setName(parameter.getName());
					parameterInfo.setError("RequestParam annotation에 value가 없음");
				} else {
					parameterInfo.setName(requestParamAnnotation.value());
					
					Class<?> type = parameter.getType();
					if (type.isArray()) {
						parameterInfo.setArray(true);
						parameterInfo.setType(getType(type.getComponentType()));
					} else {
						parameterInfo.setType(getType(type));
					}
					parameterInfo.setRequire(requestParamAnnotation.required());
				}
			} else if (parameter.getAnnotation(RequestHeader.class) != null) {
				continue;
			} else if (parameter.getAnnotation(Value.class) != null ||
					parameter.getType() == HttpServletRequest.class ||
					parameter.getType() == HttpServletResponse.class ||
					parameter.getType() == ModelAndView.class ||
					parameter.getType() == View.class ||
					parameter.getType() == HttpSession.class
					) {
				continue;
			} else {
				parameterInfo.setName(parameter.getName());
				parameterInfo.setError("PathVariable이나 RequestParam annotation이 없음");
			}
			
			api.getParameters().add(parameterInfo);
		}
	}
	
	private void setSession(ApiInfo api, Method method) {
		Session sessionAnnotation = method.getAnnotation(Session.class);
		if (sessionAnnotation != null ) {
			if (sessionAnnotation.required() ) {
				api.setNeedSession(true);
			}
		} else {
			api.setNeedSession(true);
		}
	}
	
	private void setDescription(ApiInfo api, Method method) {
		Description descriptionAnnotation = method.getAnnotation(Description.class);
		if (descriptionAnnotation != null) {
			api.setDescription(descriptionAnnotation.value());
		}
	}
	
	private void setHistory(ApiInfo api, Method method) {
		Histories historiesAnnotation = method.getAnnotation(Histories.class);
		if (historiesAnnotation != null) {
			for (History historyAnnotation: historiesAnnotation.value()) {
				api.getHistories().add(new HistoryInfo(historyAnnotation.date(), historyAnnotation.description()));
			}
		}
	}
	
	private void setValidation(ApiInfo api, Method method) {
		Class<?>[] exceptions = method.getExceptionTypes();
		for (Class<?> exception: exceptions) {
			try {
				Constructor<?> constructor = exception.getConstructor(null);
				Exception obj = (Exception) constructor.newInstance(null);
				api.getValidations().add(obj.toString());
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private int setResult(Map<String, ApiInfo.Values<String, String, String, Object>> resultMap, Class<?> type, int depth) {
		List<Field> fieldList = new ArrayList<Field>();
		ClassUtils.getSuperFields(type, fieldList);
		
		ApiInfo.Values<String, String, String, Object> pair;
		for (Field field: fieldList) {
			if ("serialVersionUID".equals(field.getName())) {
				continue;
			}
			
			Description descriptionAnnotation = field.getAnnotation(Description.class);
			String description = descriptionAnnotation == null ? "" : descriptionAnnotation.value();
			
			Class<?> t = field.getType();
			if (Integer.class.isAssignableFrom(t) || int.class.isAssignableFrom(t) ||
					Long.class.isAssignableFrom(t) || long.class.isAssignableFrom(t) ||
					Float.class.isAssignableFrom(t) || float.class.isAssignableFrom(t) ||
					Double.class.isAssignableFrom(t) || double.class.isAssignableFrom(t) ||
					String.class.isAssignableFrom(t) || Boolean.class.isAssignableFrom(t)
					) {
				String h = null;
				if (String.class.isAssignableFrom(t)) {
					String methodName = "set" + ClassUtils.capitalize(field.getName());
					List<Method> methods = ClassUtils.getMethods(type, methodName);
					if (methods != null && methods.size() > 0) {
						HeaderValue headerValueAnno =  methods.get(0).getAnnotation(HeaderValue.class);
						if (headerValueAnno != null) {
							h = headerValueAnno.value();
						}
					}
				}
				pair = new ApiInfo.Values<String, String, String, Object>(t.getSimpleName(), description, h, null);
			} else if (List.class.isAssignableFrom(t)) {
				ParameterizedType pt = (ParameterizedType) field.getGenericType();
				Class<?> at = (Class<?>) pt.getActualTypeArguments()[0];
				//logger.error("at: {}", at);
				
				Map<String, ApiInfo.Values<String, String, String, Object>> map = new TreeMap<String, ApiInfo.Values<String, String, String, Object>>();
				if (Integer.class.isAssignableFrom(at) || int.class.isAssignableFrom(at) ||
						Long.class.isAssignableFrom(at) || long.class.isAssignableFrom(at) ||
						Float.class.isAssignableFrom(at) || float.class.isAssignableFrom(at) ||
						Double.class.isAssignableFrom(at) || double.class.isAssignableFrom(at) ||
						String.class.isAssignableFrom(at) || Boolean.class.isAssignableFrom(t) ||
						Enum.class.isAssignableFrom(at) ||
						type == at	// 클래스안에서 리스트로 자신과 같은 클래스를 field로 가지는 경우 무한 재귀 호출
						) {
				} else {
					depth = Math.max(depth, setResult(map, at, depth));
				}
				
				pair = new ApiInfo.Values<String, String, String, Object>(t.getSimpleName(), description, at.getSimpleName(), map);
			} else {
				Map<String, ApiInfo.Values<String, String, String, Object>> map = new TreeMap<String, ApiInfo.Values<String, String, String, Object>>();
				depth = Math.max(depth, setResult(map, t, depth));
				
				pair = new ApiInfo.Values<String, String, String, Object>(t.getSimpleName(), description, null, map);
			}
			
			resultMap.put(field.getName(), pair);
		}
		
		return depth + 1;
	}

	private PARAMETER_TYPE getType(Class<?> type) {
		if (type == String.class) {
			return PARAMETER_TYPE.STRING;
		} else if (type == Integer.class || type == int.class) {
			return PARAMETER_TYPE.INTEGER;
		} else if (type == Long.class || type == long.class) {
			return PARAMETER_TYPE.LONG;
		} else if (type == Float.class || type == float.class) {
			return PARAMETER_TYPE.FLOAT;
		} else if (type == Double.class || type == double.class) {
			return PARAMETER_TYPE.DOUBLE;
		} else if (type == MultipartFile.class) {
			return PARAMETER_TYPE.FILE;
		}
		
		return PARAMETER_TYPE.UNKNOWN;
	}
	
}
