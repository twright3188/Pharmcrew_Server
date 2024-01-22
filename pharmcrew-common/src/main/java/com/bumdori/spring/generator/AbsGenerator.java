package com.bumdori.spring.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bumdori.spring.apitest.AbsApiMgr;
import com.bumdori.spring.apitest.ApiInfo;
import com.bumdori.spring.apitest.ApiInfo.Values;
import com.bumdori.spring.apitest.ControllerInfo;
import com.bumdori.spring.apitest.PARAMETER_TYPE;
import com.bumdori.spring.apitest.ParameterInfo;
import com.bumdori.util.ClassUtils;
import com.bumdori.util.StringUtils;

public abstract class AbsGenerator {

	private final File userDir = new File(System.getProperty("user.dir"));

	private String requestPackage;
	private String requestPath;

	private AbsApiMgr apiMgr;

	private XSSFCellStyle styleTitle;
	private XSSFCellStyle styleRequest;
	private XSSFCellStyle styleResponse;
	private XSSFCellStyle styleCenter;

	protected AbsGenerator(String project, AbsApiMgr apiMgr) {
		this.apiMgr = apiMgr; 

		requestPackage = "com.bumdori." + project + ".gen.request";
		requestPath = userDir.getAbsolutePath() + "/src/main/java/" + requestPackage.replace('.', '/') + "/";

		new File(requestPath).mkdirs();
	}

	public void makeReq() throws IOException {
		Map<String, ControllerInfo> controllers = apiMgr.getControllers();
		Set<String> keys = controllers.keySet();
		for (String key: keys) {
			ControllerInfo controller = controllers.get(key);
			Map<String, ApiInfo> apis = controller.getApis();
			Set<String> apiKeys = apis.keySet();
			for (String apiKey: apiKeys) {
				ApiInfo api = apis.get(apiKey);
				if (StringUtils.isEmpty(api.getError()) == false) {
					continue;
				}
				makeRequest(controller, api);
			}
		}
	}
	
	public void generateAPI() throws IOException {
		File file = new File(requestPath + "API.java");
		file.createNewFile();
		
		FileWriter writer = new FileWriter(file);
		
		writer.append("public enum API {\r\n\r\n");
		
		Map<String, ControllerInfo> controllers = apiMgr.getControllers();
		Set<String> keys = controllers.keySet();
		for (String key: keys) {
			ControllerInfo controller = controllers.get(key);
			Map<String, ApiInfo> apis = controller.getApis();
			Set<String> apiKeys = apis.keySet();
			for (String apiKey: apiKeys) {
				ApiInfo api = apis.get(apiKey);
				if (StringUtils.isEmpty(api.getError()) == false) {
					continue;
				}
				writer.append("\t" + api.getFunction().getName().toUpperCase() + ",\t\t\t\t//\t" +  api.getDescription() + "\r\n");
			}
		}
		writer.append("\t;\r\n\r\n");
		
		writer.append("\tpublic static API valueOf(int ordinal) {\r\n");
		writer.append("\t\treturn values()[ordinal];\r\n");
		writer.append("\t}\r\n");
		
		writer.append("}\r\n");
		writer.flush();
		writer.close();
	}

	public void genereateNetworkTest() throws IOException {
		
		File file = new File(requestPath + "NetworkTestActivity.java");
		file.createNewFile();
		
		FileWriter writer = new FileWriter(file);
		
		writer.append("\r\n");
		writer.append("\r\n");
		writer.append("import android.app.Activity;\r\n");
		writer.append("import android.os.Bundle;\r\n");
		writer.append("\r\n");
		writer.append("import com.bumdori.android.thread.WorkInThread;\r\n");
		writer.append("import com.bumdori.network.http.BHttpException;\r\n");
		writer.append("import com.bumdori.haru.gen.request.AuthControllerAuthPhoneRequest;\r\n");
		writer.append("import kr.co.sigongedu.haru.api.resp.auth.AuthResp;\r\n");
		writer.append("import com.sigongedu.haru.network.API;\r\n");
		writer.append("import com.sigongedu.haru.network.Constants;\r\n");
		writer.append("\r\n");
		writer.append("import org.json.JSONException;\r\n");
		writer.append("\r\n");
		writer.append("import java.io.IOException;\r\n");

		writer.append("\r\n");
		
		writer.append("public class NetworkTestActivity extends Activity implements WorkInThread.OnResultListener  {\r\n");
		writer.append("\r\n");
		
		writer.append("\tprivate String TAG = NetworkTestActivity.class.getSimpleName();\r\n");
		writer.append("\r\n");
		
		writer.append("\tprivate Context mContext;\r\n");
		writer.append("\t\r\n");
		writer.append("\t@Override\r\n");
		writer.append("\tprotected void onCreate(Bundle savedInstanceState) {\r\n");
		writer.append("\t\tsuper.onCreate(savedInstanceState);\r\n");
		writer.append("\t\tmContext = this;\r\n");
		writer.append("\t}\r\n");

		writer.append("\r\n");

		Map<String, ControllerInfo> controllers = apiMgr.getControllers();
		Set<String> keys = controllers.keySet();
		for (String key: keys) {
			ControllerInfo controller = controllers.get(key);
			Map<String, ApiInfo> apis = controller.getApis();
			Set<String> apiKeys = apis.keySet();
			for (String apiKey: apiKeys) {
				ApiInfo api = apis.get(apiKey);
				if (StringUtils.isEmpty(api.getError()) == false) {
					continue;
				}
				String reqName = ClassUtils.capitalize(controller.getClassName()) + ClassUtils.capitalize(api.getFunction().getName()) + "Request";
				
				writer.append("\t/**\r\n");
				writer.append("\t * " + api.getDescription() + "\r\n");
				writer.append("\t * \r\n");
				
				List<ParameterInfo> parameters = api.getPathParameters();
				parameters.addAll(api.getParameters());
				
				if (parameters != null && parameters.size()>0) {
					for (ParameterInfo parameter: parameters) {
						writer.append("\t * @param " + parameter.getName().toLowerCase() + " : " + parameter.getDescription() + "\r\n");
					}
				}
				writer.append("\t * @return void : " + api.getResponse().getSimpleName() + "\r\n");
				writer.append("\t */\r\n");
				String funcName = "\tprivate void " + " request" + firstUpper(api.getFunction().getName()) + "(";
				writer.append(funcName);
				
				if (parameters != null && parameters.size()>0) {
					int addedCount = 0;
					for (ParameterInfo param : parameters) {
						writer.append("\r\n");
						appendBlank(writer, funcName.length());
						writer.append("final " + param.getAndroidType() + " " + param.getName().toLowerCase());
						addedCount++;
						if (addedCount<parameters.size()) {
							writer.append(" , ");	
						}
						writer.append("\t\t\t//\t" + param.getDescription());
					}
					writer.append("\r\n");
					appendBlank(writer, funcName.length());
				}
				
				writer.append(") {\r\n\r\n");
				writer.append("\t\tBundle data = new Bundle();\r\n");
				writer.append("\r\n");
				writer.append("\t\tnew WorkInThread(this, API."+ api.getFunction().getName().toUpperCase() + ".ordinal(), this) {\r\n");
				writer.append("\r\n");
				writer.append("\t\t\t@Override\r\n");
				writer.append("\t\t\tpublic void doWork(Bundle arg0) throws BHttpException, IOException, JSONException {\r\n");
				writer.append("\r\n");
				writer.append("\t\t\t\t" + reqName + " req = new " + reqName + "(Constants.API_SCHEME, Constants.API_HOST");
				if (api.isNeedSession()) {
					writer.append(", Constants.COOKIES");
				}
				writer.append(");\r\n");
				writer.append("\r\n");
				
				if (parameters != null) {
					for (ParameterInfo param : parameters) {
						writer.append("\t\t\t\treq.set" + ClassUtils.capitalize(param.getName()) + "(" + param.getName().toLowerCase() + ");");
						writer.append("\r\n");
					}
				}
				writer.append("\r\n");
				writer.append("\t\t\t\t" + api.getResponse().getSimpleName() + " resp = req.go();\r\n");
				writer.append("\t\t\t\targ0.putSerializable(\"resp\", resp);\r\n");
				writer.append("\t\t\t}\r\n");
				writer.append("\t\t}.setData(data).setProgress(true).setMessage(\"" + api.getDescription() + "\").start();\r\n");
				writer.append("\t}\r\n");
				writer.append("\r\n");
				
			}
		}

		writer.append("\t//********************************************************************************\r\n");
		writer.append("\t//  Network Function\r\n");
		writer.append("\t//********************************************************************************\r\n");

		writer.append("\t\r\n");
		writer.append("\t//********************************************************************************\r\n");
		writer.append("\t//  Network Response\r\n");
		writer.append("\t//********************************************************************************\r\n");
		writer.append("\t\r\n");

		writer.append("\t@Override\r\n");
		writer.append("\tpublic boolean onSuccess(int id, Bundle data) {\r\n");
		
		writer.append("\t\tHaruResp tmp = (HaruResp) data.getSerializable(\"resp\");\r\n");
		writer.append("\t\tLog.d(TAG, \"tmp: \" + tmp.toJSONString());\r\n");
		writer.append("\r\n");
		
		writer.append("\t\tif (tmp.getCode() != VALIDATION.OK.getCode()) {\r\n");
		writer.append("\t\t\tonFail(id, tmp.getCode(), tmp.getMessage());\r\n");
		writer.append("\t\t\treturn true;\r\n");
		writer.append("\t\t}\r\n");
		writer.append("\r\n");
		
		writer.append("\t\tswitch (API.valueOf(id)) {\r\n");

		for (String key: keys) {
			ControllerInfo controller = controllers.get(key);
			Map<String, ApiInfo> apis = controller.getApis();
			Set<String> apiKeys = apis.keySet();
			for (String apiKey: apiKeys) {
				ApiInfo api = apis.get(apiKey);
				if (StringUtils.isEmpty(api.getError()) == false) {
					continue;
				}
				writer.append("\t\t\tcase " + api.getFunction().getName().toUpperCase() + ": {\t\t\t\t//\t" +  api.getDescription() + "\r\n");
				writer.append("\t\t\t\tfinal " + api.getResponse().getSimpleName() + " resp = (" + api.getResponse().getSimpleName() + ") tmp;\r\n");
				writer.append("\t\t\t\tbreak;\r\n");
				writer.append("\t\t\t}\r\n");
			}
		}	
		writer.append("\t\t}\r\n");
		
		writer.append("\t\treturn true;\r\n");

		writer.append("\t}\r\n");
		
		writer.append("\t\r\n");
		
		writer.append("\t@Override\r\n");
		writer.append("\tpublic void onFail(int id, int code, String message) {\r\n");
		writer.append("\t\tLog.w(TAG, \"onFail: \" + id + \" , \" + code + \" , \" + message);\r\n");
		writer.append("\t\tToast.makeText(mContext, message, Toast.LENGTH_SHORT).show();\r\n");
		writer.append("\t}\r\n");
		
		writer.append("\t\r\n");
		
		writer.append("}\r\n");
		writer.flush();
		writer.close();
	}

	private void appendBlank(FileWriter writer, int count) throws IOException{
		for (int i = 0; i < count; i++) {
			writer.append(" ");
		}
	}
	
	public void makeAppleReq() throws IOException {
		Map<String, ControllerInfo> controllers = apiMgr.getControllers();

		Set<String> keys = controllers.keySet();
		for (String key: keys) {
			ControllerInfo controller = controllers.get(key);
			System.out.println("controller : " + controller);
			String name = ClassUtils.capitalize(controller.getClassName());
			try {
//				generateAppleHeader(name, controller, controller.getApis());
				generateAppleSource(name, controller, controller.getApis());
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		try {
//			generateAppleCaller(controllers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Apple 헤더 생성 코드
	 * @param name
	 * @param controller
	 * @param apis
	 * @throws Exception
	 */
	private void generateAppleHeader(String name, ControllerInfo controller, Map<String, ApiInfo> apis)  throws Exception {
		File file = new File(requestPath + name + ".h");
		file.createNewFile();
		
		FileWriter writer = new FileWriter(file);
		
		writer.append("//\r\n");
		writer.append("//  " + name + ".h\r\n");
		writer.append("//  AFNetworking\r\n");
		writer.append("//\r\n");
		writer.append("//  Created by Sungs on 2020. 05. 20.\r\n");
		writer.append("//  Copyright © 2020 Sungs. All rights reserved.\r\n");
		writer.append("//\r\n");

		writer.append("#ifndef " + name + "_h\r\n");
		writer.append("#define " + name + "_h\r\n");
		writer.append("\r\n");

		writer.append("#import \"RequestManager.h\"\r\n");
		
		writer.append("@interface " + name + " : NSObject\r\n");
		writer.append("\r\n");

		Set<String> apiKeys = apis.keySet();
		for (String apiKey: apiKeys) {
			ApiInfo api = apis.get(apiKey);
			if (StringUtils.isEmpty(api.getError()) == false) {
				continue;
			}

			List<ParameterInfo> parameters = api.getPathParameters();
			parameters.addAll(api.getParameters());

			writer.append("// " + api.getDescription() + "\r\n");

			String funcName = "-(void)" + api.getFunction().getName();
			int funcLength = funcName.length();
			writer.append(funcName);

			int addedCount = 0;
			if (parameters != null && parameters.size()>0) {
					for (ParameterInfo parameter: parameters) {
						if (addedCount == 0) {
							writer.append("With");
							writer.append( firstUpper(parameter.getName()) + ":(" + parameter.getAppleType() + ")" + parameter.getName() + "\r\n");
							funcLength += String.format("%s%s", "With", firstUpper(parameter.getName())).length();
						} else {
							appendBlank(writer, funcLength - parameter.getName().length());
							writer.append( parameter.getName() + ":(" + parameter.getAppleType() + ")" + parameter.getName() + "\r\n");
						}
						addedCount++;
					}
					appendBlank(writer, funcLength-7);
					writer.append("success:(void (^)(NSURLSessionDataTask *task, id responseObject))success\r\n");
					appendBlank(writer, funcLength-7);
					writer.append("failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;\r\n");
					
			} else {
				writer.append("Success:(void (^)(NSURLSessionDataTask *task, id responseObject))success\r\n");
				appendBlank(writer, funcLength);
				writer.append("failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;\r\n");
			}
			writer.append("\r\n\r\n");
		}


		writer.append("@end\r\n");

		writer.append("#endif /* " + name + "_h */\r\n");

		writer.flush();
		writer.close();
	}

	private String firstUpper(String data) {
		String trans = data.substring(0, 1);
		trans = trans.toUpperCase();
		trans += data.substring(1);
		return trans;
	}
	
	private void generateAppleSource(String name, ControllerInfo controller, Map<String, ApiInfo> apis)  throws Exception {

		File file = new File(requestPath + name + ".m");
		file.createNewFile();

		FileWriter writer = new FileWriter(file);

		writer.append("//\r\n" 
				+ "//\t" + name + ".m\r\n"
				+ "//\tAFNetworking\r\n"
				+ "//\r\n"
				+ "//\t Created by Sungs on 2020. 05. 20.\r\n"
				+ "//\t Copyright © 2020 Sungs. All rights reserved.\r\n"
				+ "\r\n");
		writer.append("#import \"" + name + ".h\"\r\n\r\n");

		writer.append("@implementation " + name + "\r\n\r\n");

		Set<String> apiKeys = apis.keySet();
		for (String apiKey: apiKeys) {
			ApiInfo api = apis.get(apiKey);
			if (StringUtils.isEmpty(api.getError()) == false) {
				continue;
			}

			List<ParameterInfo> parameters = api.getPathParameters();
			parameters.addAll(api.getParameters());

			writer.append("/**\r\n");
			writer.append(" * " + api.getDescription() + "\r\n");
			 
			if (parameters != null) {
				for (ParameterInfo parameter: parameters) {
					writer.append(" * @param " + parameter.getName() + " : " + parameter.getDescription() + "\r\n");
				}
			}
			//writer.append(" * @return void : " + api.getResponse().getSimpleName() + "\r\n");
			writer.append(" */\r\n");
				
			String funcName = "-(void)" + api.getFunction().getName();
			int funcLength = funcName.length();
			writer.append(funcName);
			
			int addedCount = 0;
			if (parameters != null &&parameters.size()>0) {
					for (ParameterInfo parameter: parameters) {
						if (addedCount == 0) {
							writer.append("With");
							writer.append( firstUpper(parameter.getName()) + ":(" + parameter.getAppleType() + ")" + parameter.getName() + "\r\n");
							funcLength += String.format("%s%s", "With", firstUpper(parameter.getName())).length();
						} else {
							appendBlank(writer, funcLength - parameter.getName().length());
							writer.append( parameter.getName() + ":(" + parameter.getAppleType() + ")" + parameter.getName() + "\r\n");
						}
						addedCount++;
					}
					appendBlank(writer, funcLength-7);
					writer.append("success:(void (^)(NSURLSessionDataTask *task, id responseObject))success\r\n");
					appendBlank(writer, funcLength-7);
					writer.append("failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {\r\n");
			} else {
				writer.append("Success:(void (^)(NSURLSessionDataTask *task, id responseObject))success\r\n");
				appendBlank(writer, funcLength);
				writer.append("failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {\r\n");
			}
			writer.append("\r\n");
			
			
			// 서버 URL
			writer.append("\tNSString* URL = @\"" + api.getUrl() + "\";\r\n");
			writer.append("\r\n");

			writer.append("\t//파라메터 구성\r\n");			
			if (parameters != null && parameters.size() > 0 ) {
				for (ParameterInfo parameter: parameters) {
					if(parameter.getType() != PARAMETER_TYPE.STRING) {
						switch (parameter.getType()) {
						case FILE:
						case FLOAT:
						case DOUBLE:
							writer.append("\tNSString* string" + firstUpper(parameter.getName()) + " = [NSString stringWithFormat:@\"%lf\", " + parameter.getName() + "];\r\n");							
							break;
						case LONG:
						case INTEGER:
							writer.append("\tNSString* string" + firstUpper(parameter.getName()) + " = [NSString stringWithFormat:@\"%ld\", " + parameter.getName() + "];\r\n");
							break;
						default:
							Exception e = new Exception("파라메터 ==> 인식되지 않은 타입 " + parameter.getName() + ", " +parameter.getDescription());
							System.out.println(e.getMessage());
//							throw e;
						}
					}
				}
				writer.append("\r\n");
			}
			
			String dicString = "\tNSMutableDictionary* param = ";
			if (parameters != null && parameters.size()>0) {
				writer.append( dicString + "[NSMutableDictionary dictionaryWithObjectsAndKeys:\r\n");//이 라이은 모두 동일
				for (ParameterInfo parameter: parameters) {
					appendBlank(writer, dicString.length());
					if(parameter.getType() != PARAMETER_TYPE.STRING) {
						switch (parameter.getType()) {
						case FILE:
						case FLOAT:
						case DOUBLE:
							writer.append("string" + firstUpper(parameter.getName()) + ",@\"" + parameter.getName() + "\"");							
							break;
						case LONG:
						case INTEGER:
							writer.append("string" + firstUpper(parameter.getName()) + ",@\"" + parameter.getName() + "\"");
							break;
						default:
							Exception e = new Exception("Reqpuest ==> 인식되지 않은 타입 " + parameter.getName() + ", " +parameter.getDescription());
							System.out.println(e.getMessage());
							writer.append(parameter.getName() + ",@\"" + parameter.getName() + "\"");
						}
					} else {
						writer.append(parameter.getName() + ",@\"" + parameter.getName() + "\"");
					}
					writer.append(",\r\n");
				}
				appendBlank(writer, dicString.length());
				writer.append("nil];\r\n");
			} else {
				writer.append(dicString + "nil;\r\n");
			}
			writer.append("\r\n");
						
			writer.append("\t//////////////회신 정보 참조용//////////////\r\n");
			writer.append("\t/*\r\n");
			generateAppleResponse(writer, 1, api.getResults());
			writer.append("\t*/\r\n");
			writer.append("\r\n");
			
			writer.append("\t// " + api.getMethod().name() + "으로 요청\r\n");
			writer.append("\t[[RequestManager sharedManager] request" + api.getMethod().name() + ":URL parameters:param success:success failure:failure];\r\n");
			
			writer.append("}\r\n");
			writer.append("\r\n");
			writer.append("\r\n");
		}
		writer.append("@end\r\n");
		writer.flush();
		writer.close();
	}

	private void generateAppleResponse(FileWriter writer, int depth, Map<String, Values<String, String, String, Object>> results) throws Exception {
		Iterator<String> itr = results.keySet().iterator();
		while (itr.hasNext()) {

			String key = itr.next();
			Values<String, String, String, Object> value = results.get(key);
			
			// 요청 아이디 삭제 
			if ("reqId".equals(key)) {
				continue;
			}
			
			if ("code".equals(key)) {
				for (int i = 0; i < depth; i++) {
					writer.append("\t");
				}
				writer.append("NSInteger code = [[result objectForKey:@\"" + key + "\"] integerValue];\t\t\t\t// " + "회신 코드" + "\r\n");
			} else if ("message".equals(key)) {
				for (int i = 0; i < depth; i++) {
					writer.append("\t");
				}
				writer.append("NSString* msg = [result objectForKey:@\"" + key + "\"];\t\t\t\t\t\t// " + "회신 메시지" + "\r\n");
			} else if ("String".equals(value.getT())) {
				for (int i = 0; i < depth; i++) {
					writer.append("\t");
				}
				writer.append("NSString* " + key + " = [result objectForKey:@\"" + key + "\"];\t\t\t\t\t\t// " + value.getD() + "\r\n");
			} else if ("Integer".equals(value.getT())) {
				for (int i = 0; i < depth; i++) {
					writer.append("\t");
				}
				writer.append("NSInteger " + key + " = [[result objectForKey:@\"" + key + "\"] integerValue];\t\t\t\t// " + value.getD() + "\r\n");
			} else if ("Long".equals(value.getT())) {
				for (int i = 0; i < depth; i++) {
					writer.append("\t");
				}
				writer.append("NSInteger " + key + " = [[result objectForKey:@\"" + key + "\"] doubleValue];\t\t\t\t// " + value.getD() + "\r\n");
			} else if ("List".equals(value.getT())) {
				for (int i = 0; i < depth; i++) {
					writer.append("\t//");
				}
				writer.append("NSArray* " + key + " = [[result objectForKey:@\" " + key + "\"] allKeys];\t\t\t\t// " + value.getD() + " ==> 리스트 구조체\r\n");
			} else {
				Exception e = new Exception("Response ==> 인식되지 않은 타입 " + key + ", " +  value.getT() + " , " + value.getD() ) ;
				System.out.println(e.getMessage());
				for (int i = 0; i < depth; i++) {
					writer.append("\t//");
				}
				writer.append("NSString* " + key + " = [[result objectForKey:@\" " + key + "\"] objectValue];\t\t\t\t// " + value.getD() + " ==> 정보 구조체\r\n");
			}
			if (value.getV() != null) {
				generateAppleResponse(writer, ++depth,  (Map<String, Values<String, String, String, Object>>) value.getV());
				depth -= 1;
			}
		}
	}
	
	private void generateAppleCaller(Map<String, ControllerInfo> controllers)  throws Exception {
		
		File file = new File(requestPath + "NetworkCall.m");
		file.createNewFile();

		FileWriter writer = new FileWriter(file);

		writer.append("//\r\n" 
				+ "//\t" + "NetworkCall.m\r\n"
				+ "//\tAFNetworking\r\n"
				+ "//\r\n"
				+ "//\t Created by Sungs on 2020. 05. 20.\r\n"
				+ "//\t Copyright © 2020 Sungs. All rights reserved.\r\n"
				+ "\r\n");


		Set<String> keys = controllers.keySet();

		// import
		for (String key: keys) {
			ControllerInfo controller = controllers.get(key);
			System.out.println("controller : " + controller);
			String name = ClassUtils.capitalize(controller.getClassName());
			writer.append("#import \"" + name + ".h\"\r\n");
		}

		writer.append("\r\n");
		
		writer.append("@interface NetworkCall : NSObject\r\n");
		writer.append("\r\n");
		writer.append("@end\r\n");
		
		writer.append("\r\n");

		writer.append("@implementation " + "NetworkCall\r\n\r\n");
		for (String key: keys) {
			ControllerInfo controller = controllers.get(key);
			System.out.println("controller : " + controller);
			
			String name = ClassUtils.capitalize(controller.getClassName());
			
			Map<String, ApiInfo> apis = controller.getApis();
			
			Set<String> apiKeys = apis.keySet();
			
			for (String apiKey: apiKeys) {
				ApiInfo api = apis.get(apiKey);
				if (StringUtils.isEmpty(api.getError()) == false) {
					continue;
				}

				List<ParameterInfo> parameters = api.getPathParameters();
				parameters.addAll(api.getParameters());
				
				writer.append("/**\r\n");
				writer.append(" * " + api.getDescription() + "\r\n");

//				if (parameters != null) {
//					for (ParameterInfo parameter: parameters) {
//						writer.append(" * @param " + parameter.getName() + " : " + parameter.getDescription() + "\r\n");
//					}
//				}

				writer.append(" */\r\n");

				String funcName = "-(void)" + api.getFunction().getName() + "{\r\n";
				int funcLength = funcName.length();
				writer.append(funcName);
				writer.append("\r\n");
				
				int addedCount = 0;
				if (parameters != null &&parameters.size()>0) {
					for (ParameterInfo parameter: parameters) {
						if(parameter.getType() != PARAMETER_TYPE.STRING) {
							switch (parameter.getType()) {
							case FILE:
							case FLOAT:
							case DOUBLE:
								writer.append("\tNSString* " + parameter.getName() + " = @\"\";\r\n");							
								break;
							case LONG:
							case INTEGER:
								writer.append("\tNSInteger " + parameter.getName() + " = 1;\r\n");
								break;
							}
						} else {
							writer.append("\t" + parameter.getAppleType() + " " + parameter.getName() + " = @\"\";\r\n");
						}

					}
				}
				
				writer.append("\r\n");
				// 컨트롤 생성
				writer.append("\t" + name + "* api = [" + name + " new];\r\n");
				
				// api 함수 호출
				writer.append("\t[api " + api.getFunction().getName());
				funcLength = String.format("\t%s%s", "[api ", api.getFunction().getName()).length();
				addedCount = 0;
				if (parameters != null &&parameters.size()>0) {
					for (ParameterInfo parameter: parameters) {
						if (addedCount == 0) {
							writer.append("With");
							writer.append( firstUpper(parameter.getName()) + ":" + parameter.getName() + "\r\n");
							funcLength += String.format("%s%s", "With", firstUpper(parameter.getName())).length();
						} else {
							appendBlank(writer, funcLength - parameter.getName().length());
							writer.append(parameter.getName() + ":" + parameter.getName() + "\r\n");
						}
						addedCount++;
					}
					
					writer.append("\tsuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {\r\n");
				} else {
					writer.append("Success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {\r\n");
				}
				
				writer.append("\r\n");
				writer.append("\t\tNSDictionary* result = responseObject;\r\n");
				writer.append("\t\tNSLog(@\"Response : %@\", result.description);\r\n");
				writer.append("\r\n");
				
				writer.append("\t\t\tNSInteger code = [[result objectForKey:@\"code\"] integerValue];				// 회신 코드\r\n");
				writer.append("\t\t\tNSString* msg = [result objectForKey:@\"message\"];						// 회신 메시지\r\n");
				writer.append("\r\n");
				
				writer.append("\t\tif (code == 200) {\r\n");
				writer.append("\t\t\t// 요청 성공 \r\n");

				writer.append("\t\t\t//////////////회신 정보 참조용//////////////\r\n");
				writer.append("\t\t\t/*\r\n");
				generateAppleResponse(writer, 3, api.getResults());
				writer.append("\t\t\t*/\r\n");
				writer.append("\r\n");
				
				writer.append("\t\t} else {\r\n");
				writer.append("\t\t\t// 서버 내부 오류 \r\n");
				writer.append("\t\t\tNSLog(@\"Response : %@\", msg);\r\n");
				writer.append("\t\t}\r\n");
				writer.append("\t} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {\r\n");
				writer.append("\t\t// 서버 외부 오류 \r\n");
				writer.append("\t\tNSLog(@\"Error : %@\", error.localizedDescription);\r\n");
				writer.append("\t}];\r\n");

				writer.append("}\r\n");
				writer.append("\r\n");
				writer.append("\r\n");
			}

		}
		
		writer.append("@end\r\n");
		writer.flush();
		writer.close();
	}
	
	public void makeDoc(XSSFWorkbook wb) throws NoSuchFieldException, SecurityException {
		initDocStyles(wb);
		
		Map<String, ControllerInfo> controllerMap = apiMgr.getControllers();
		
		Iterator<String> itr = controllerMap.keySet().iterator();
		while (itr.hasNext()) {
			int depth = 0;
			int rowIdx = 0;
			
			String key = itr.next();
			
			XSSFSheet sheet = wb.createSheet(key);
			ControllerInfo controller = controllerMap.get(key);
			
			Map<String, ApiInfo> apiMap = controller.getApis();
			Iterator<String> itra = apiMap.keySet().iterator();
			while (itra.hasNext()) {
				String apiKey = itra.next();
				ApiInfo api = apiMap.get(apiKey);
				depth = api.getResultDepth();
				
				XSSFRow row;
				
				int nameStartIdx = rowIdx;
				
				row = sheet.createRow(rowIdx++);
				// 이름
				XSSFCell cell = row.createCell(0);
				cell.setCellValue(api.getName());
				cell.setCellStyle(styleCenter);
				// 설명
				makeDocApiDesc(depth, rowIdx, sheet, api, row);

				row = sheet.createRow(rowIdx++);
				// path
				makeDocApiPath(depth, rowIdx, sheet, api, row);

				row = sheet.createRow(rowIdx++);
				// http method
				makeDocApiMethod(depth, rowIdx, sheet, api, row);

				row = sheet.createRow(rowIdx++);
				// request
				rowIdx = makeDocApiRequest(depth, rowIdx, sheet, api, row);

				row = sheet.createRow(rowIdx++);
				// response
				rowIdx = makeDocApiResponse(depth, rowIdx, sheet, api, row);

				sheet.addMergedRegion(new CellRangeAddress(nameStartIdx, rowIdx - 1, 0, 0));	// 이름
			}
		}
	}

	private void makeRequest(ControllerInfo controller, ApiInfo api) throws IOException {
		String name = ClassUtils.capitalize(controller.getClassName())
				+ ClassUtils.capitalize(api.getFunction().getName()) + "Request";

		File file = new File(requestPath + name + ".java");
		file.createNewFile();

		FileWriter writer = new FileWriter(file);
		writer.append("package " + requestPackage + ";\r\n\r\n"
				+ "import java.io.File;\r\n"
				+ "import java.io.IOException;\r\n"
				+ "import java.io.InputStream;\r\n"
				+ "import java.net.HttpURLConnection;\r\n"
				+ "import java.util.ArrayList;\r\n"
				+ "import java.util.List;\r\n\r\n"
				+ "import org.json.JSONException;\r\n"
				+ "import org.json.JSONObject;\r\n\r\n"
				+ "import com.bumdori.spring.generator.AbsRequest;\r\n"
				//				+ "import com.bumdori.bserver.request.annotation.API;\r\n"
				//				+ "import com.bumdori.bserver.request.annotation.PARAM;\r\n"
				+ "import " + api.getResponse().getName() + ";\r\n"
				+ "import com.bumdori.network.http.BHttpException;\r\n"
				+ "import com.bumdori.util.StringUtils;\r\n\r\n"
				+ "public class ");
		writer.append(name + " extends AbsRequest {\r\n\r\n");

		List<ParameterInfo> parameters = api.getParameters();
		if (parameters != null) {
			for (ParameterInfo parameter: parameters) {
				writer.append(parameter.makeRequestField());
				writer.append("\r\n");
			}
		}
		writer.append("\r\n");

		writer.append("	public " + name + "(String scheme, String host");
		if (api.isNeedSession()) {
			writer.append(", String session");
		}
		writer.append(") {\r\n");
		writer.append("		super(scheme, host, \"" + api.getUrl() + "\"" + ", \"" + api.getMethod().name() + "\"");
		if (api.isNeedSession()) {
			writer.append(", session");
		} else {
			writer.append(", null");
		}
		writer.append(");\r\n");
		writer.append("	}\r\n\r\n");

		writer.append("	public " + api.getResponse().getSimpleName() + " go() throws BHttpException, JSONException, IOException {\r\n");
		//		writer.append("		try {\r\n");
		writer.append("			HttpURLConnection conn = send();\r\n");
		//		writer.append("			return new " + api.getResponse().getSimpleName() + "(conn);\r\n");
		writer.append("			InputStream is = conn.getInputStream();\r\n\r\n");
		writer.append("			" + api.getResponse().getSimpleName() + " response = new " + api.getResponse().getSimpleName() + "(new JSONObject(StringUtils.valueOf(is)));\r\n");//, conn.getHeaderFields());\r\n\r\n");
		writer.append("			setHeaderValue(response);\r\n\r\n");
		writer.append("			is.close();\r\n");
		writer.append("			conn.disconnect();\r\n\r\n");
		writer.append("			return response;\r\n");
		//		writer.append("		} catch (BHttpException e) {\r\n");
		//		writer.append("			e.printStackTrace();\r\n");
		//		writer.append("		} catch (JSONException e) {\r\n");
		//		writer.append("			e.printStackTrace();\r\n");
		//		writer.append("		} catch (IOException e) {\r\n");
		//		writer.append("			e.printStackTrace();\r\n");
		//		writer.append("		}\r\n\r\n");
		//		writer.append("		return null;\r\n");
		writer.append("	}\r\n\r\n");

		List<ParameterInfo> pathParametiers = api.getPathParameters();
		if (pathParametiers != null) {
			for (ParameterInfo param : pathParametiers) {
				writer.append(param.makeUrlRequestSetter());
				writer.append("\r\n");
			}
		}

		//		List<Param> params = api.getParamList();
		if (parameters != null) {
			for (ParameterInfo param : parameters) {
				writer.append(param.makeRequestGetter());
				writer.append("\r\n");
				writer.append(param.makeRequestSetter());
				writer.append("\r\n");
			}
		}

		writer.append("}\r\n");
		writer.flush();
		writer.close();
	}

	private void initDocStyles(XSSFWorkbook wb) {
		styleTitle = wb.createCellStyle();
		styleTitle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		styleTitle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		styleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		styleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		styleRequest = wb.createCellStyle();
		styleRequest.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		styleRequest.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		styleRequest.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		styleRequest.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		styleResponse = wb.createCellStyle();
		styleResponse.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		styleResponse.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		styleResponse.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		styleResponse.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		styleCenter = wb.createCellStyle();
		styleCenter.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		styleCenter.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		XSSFFont bold = wb.createFont();
		bold.setBold(true);
		styleTitle.setFont(bold);
		styleRequest.setFont(bold);
		styleResponse.setFont(bold);
	}

	

	
	
//	private int getApixlsxResponseDepth(Method method, int startDepth) {
//		int depth = startDepth;
//		
//		Class<?> typeClass = method.getParameterTypes()[0];
//		if (AbsJsonObject.class.isAssignableFrom(typeClass)) {
//			startDepth++;
//			
//			Method[] methods = typeClass.getDeclaredMethods();
//			for (int k = 0; k < methods.length; k++) {
//				if (methods[k].getName().startsWith("set") ||
//						methods[k].getName().startsWith("add")) {
//					depth = Math.max(depth, getApixlsxResponseDepth(methods[k], startDepth));
//				}
//			}
//		}
//		
//		return depth;
//	}
	
	private void makeDocApiDesc(int depth, int rowIdx, XSSFSheet sheet, ApiInfo api, XSSFRow row) {
		int cellIdx = 1;
		XSSFCell cell = row.createCell(cellIdx++);
		cell.setCellValue("설명");
		cell.setCellStyle(styleTitle);
		row.createCell(cellIdx++).setCellValue((api.getDescription() == null ? "" : api.getDescription()).replaceAll("<br>", "\n"));
		sheet.addMergedRegion(new CellRangeAddress(rowIdx - 1,
				rowIdx - 1,
				cellIdx - 1,
				cellIdx - 1+ (depth - 1) * 3 + 3));
	}

	private void makeDocApiPath(int depth, int rowIdx, XSSFSheet sheet, ApiInfo api, XSSFRow row) {
		int cellIdx = 1;
		XSSFCell cell = row.createCell(cellIdx++);
		cell.setCellValue("Path");
		cell.setCellStyle(styleTitle);
		row.createCell(cellIdx++).setCellValue(api.getUrl());
		sheet.addMergedRegion(new CellRangeAddress(rowIdx - 1,
				rowIdx - 1,
				cellIdx - 1,
				cellIdx - 1+ (depth - 1) * 3 + 3));
	}

	private void makeDocApiMethod(int depth, int rowIdx, XSSFSheet sheet, ApiInfo api, XSSFRow row) {
		int cellIdx = 1;
		XSSFCell cell = row.createCell(cellIdx++);
		cell.setCellValue("Method");
		cell.setCellStyle(styleTitle);
		row.createCell(cellIdx++).setCellValue(api.getMethod().name());
		sheet.addMergedRegion(new CellRangeAddress(rowIdx - 1,
				rowIdx - 1,
				cellIdx - 1,
				cellIdx - 1+ (depth - 1) * 3 + 3));
	}

	private int makeDocApiRequest(int depth, int rowIdx, XSSFSheet sheet, ApiInfo api, XSSFRow row) {
		int cellIdx = 1;
		int requestRowStartIdx = rowIdx - 1;
		XSSFCell cell = row.createCell(cellIdx++);
		cell.setCellValue("요청");
		cell.setCellStyle(styleRequest);
		cell = row.createCell(cellIdx++);
		cell.setCellValue("파라미터");
		cell.setCellStyle(styleRequest);
		cell = row.createCell(cellIdx++);
		cell.setCellValue("형식");
		cell.setCellStyle(styleRequest);
		cell = row.createCell(cellIdx++);
		cell.setCellValue("URI");
		cell.setCellStyle(styleRequest);
		cell = row.createCell(cellIdx++);
		cell.setCellValue("설명");
		cell.setCellStyle(styleRequest);
		sheet.addMergedRegion(new CellRangeAddress(rowIdx - 1,
				rowIdx - 1,
				cellIdx - 1,
				cellIdx - 1 + (depth - 1) * 3));	// 설명 가로
	
		List<ParameterInfo> uriParams = api.getPathParameters();
		List<ParameterInfo> params = api.getParameters();
		if (uriParams.isEmpty() && params.isEmpty()) {
			row = sheet.createRow(rowIdx++);
			cellIdx = 2;
	
			row.createCell(cellIdx++).setCellValue("없음");
		} else {
			if (uriParams != null) {
				for (ParameterInfo uriParam : uriParams) {
					row = sheet.createRow(rowIdx++);
					cellIdx = 2;
	
					row.createCell(cellIdx++).setCellValue(uriParam.getName());
					row.createCell(cellIdx++).setCellValue(uriParam.getType().name());
					row.createCell(cellIdx++).setCellValue("O");
					row.createCell(cellIdx++).setCellValue((uriParam.getDescription() == null ? "" : uriParam.getDescription()).replaceAll("<br>", "\n"));
					sheet.addMergedRegion(new CellRangeAddress(rowIdx - 1,
							rowIdx - 1,
							cellIdx - 1,
							cellIdx - 1 + (depth - 1) * 3));
				}
			}
			if (params != null) {
				for (ParameterInfo param : params) {
					row = sheet.createRow(rowIdx++);
					cellIdx = 2;
	
					row.createCell(cellIdx++).setCellValue(param.getName());
					row.createCell(cellIdx++).setCellValue(param.getType().name());
					row.createCell(cellIdx++).setCellValue("");
					row.createCell(cellIdx++).setCellValue((param.getDescription() == null ? "" : param.getDescription()).replaceAll("<br>", "\n"));
					sheet.addMergedRegion(new CellRangeAddress(rowIdx - 1,
							rowIdx - 1,
							cellIdx - 1,
							cellIdx - 1 + (depth - 1) * 3));
				}
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(requestRowStartIdx,
				rowIdx - 1,
				1,
				1));	// 요청 세로
		return rowIdx;
	}

	private int makeDocApiResponse(int depth, int rowIdx, XSSFSheet sheet, ApiInfo api, XSSFRow row)
			throws NoSuchFieldException {
		int cellIdx = 1;
		int responseRowStartIdx = rowIdx - 1;
		XSSFCell cell = row.createCell(cellIdx++);
		cell.setCellValue("응답");
		cell.setCellStyle(styleResponse);
		for (int k = 1; k <= depth; k++) {
			cell = row.createCell(cellIdx++);
			cell.setCellValue("필드");
			cell.setCellStyle(styleResponse);
			cell = row.createCell(cellIdx++);
			cell.setCellValue("타입");
			cell.setCellStyle(styleResponse);
			cell = row.createCell(cellIdx++);
			cell.setCellValue("설명");
			cell.setCellStyle(styleResponse);
		}
		cell = row.createCell(cellIdx++);
		cell.setCellValue("Header");
		cell.setCellStyle(styleResponse);
		cell = row.createCell(cellIdx++);
	
		rowIdx = makeDocApiResponse(depth, rowIdx, sheet, api.getResults(), row, 1);
		
		sheet.addMergedRegion(new CellRangeAddress(responseRowStartIdx,
				rowIdx - 1,
				1,
				1));
		
		return rowIdx;
	}
	
	private int makeDocApiResponse(int depth, int rowIdx, XSSFSheet sheet, Map<String, Values<String, String, String, Object>> results, XSSFRow row, int blank) {
		int c = 0;
		Iterator<String> itr = results.keySet().iterator();
		while (itr.hasNext()) {
			int cellIdx = 2;
			
			String key = itr.next();
			Values<String, String, String, Object> value = results.get(key);
			if (blank <= 1 || c++ != 0) {
				row = sheet.createRow(rowIdx++);
			}
			cellIdx += ((blank - 1) * 3);
			row.createCell(cellIdx++).setCellValue(key);
			row.createCell(cellIdx++).setCellValue(value.getT());
			row.createCell(cellIdx++).setCellValue(value.getD());
			if (value.getH() != null) {
				row.createCell(cellIdx++).setCellValue(value.getH());
			}
			if (value.getV() != null) {
				rowIdx = makeDocApiResponse(depth, rowIdx, sheet, (Map<String, Values<String, String, String, Object>>) value.getV(), row, blank + 1);
			}
		}
		
		return rowIdx;
	}

}
