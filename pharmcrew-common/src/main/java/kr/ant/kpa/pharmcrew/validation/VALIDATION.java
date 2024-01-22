package kr.ant.kpa.pharmcrew.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VALIDATION {
    UNTREATED_EXCEPTION(100, "관리자에게 문의하세요."),

    INVALID_JSON_FORMAT(110, "JSON 형식 오류입니다."),

    OK(200, "OK"),

    // 계정/권한
    INVALID_ACCOUNT(300, "계정을 확인하세요."),
    INVALID_ACCOUNT_STATE(301, "해당 계정은 사용할 수 없는 상태입니다."),
    REQ_REGIST_ACCOUNT(302, "사용 전, 계정을 활성화 하세요."),
    NOT_USEABLE_LOGINID(303, "사용할 수 없는 아이디입니다."),
    
    NOT_FOUND_INFO(1101, "정보를 확인할 수 없습니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
    
    USER_LOGIN_FAIL(1108, "회원정보가 없거나 비밀번호가 틀립니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
    USER_NEWLOGIN_FAIL(1108, "회원정보가 없거나 틀립니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
    INVALID_MEMBER(1109, "로그인이 필요합니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),

    NOT_FOUND_MEMBER_INFO(1100, "사용자 정보가 없습니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
    NOT_FOUND_AUTH_CODE(1101, "인증번호가 틀립니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),

    NOT_FOUND_PARTNERS(1102, "파트너 정보가 틀립니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
    NOT_DELETABLE_PARTNERS(1105, "회원이 사용중인 서비스는 삭제할 수 없습니다."),

    ALREADY_REG_MEMBER(1103, "이미 등록된 회원입니다." + System.getProperty("line.separator") + "로그인 해 주세요."),
    NOT_FOUND_MEMBER_NOTI(1104, "푸시 정보가 없습니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
       
    NOT_FOUND_NOTICE(1201, "공지사항 정보를 확인할 수 없습니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
    NOT_FOUND_QNA(1202, "문의 정보를 확인할 수 없습니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
 
    NOT_FOUND_SURVEY(1301, "설문의 정보를 확인할 수 없습니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
    NOT_DELETABLE_SURVEY(1302, "참여자가 있어, 삭제할 수 없습니다."),

    NOT_FOUND_EDUCATION(1401, "교육 정보를 확인할 수 없습니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),

    NOT_FOUND_QR(1501, "QR 정보를 확인할 수 없습니다." + System.getProperty("line.separator") + "확인 후 다시 요청해 주세요."),
    INVALID_QR_START(1502, "출석 가능 QR이 아닙니다." + System.getProperty("line.separator") + "시작 QR로 요청해 주세요."),
    ALREADY_QR_START(1503, "이미 출석을 등록했습니다." + System.getProperty("line.separator") + "종료 QR로 요청해 주세요."),
    NOT_EDU_START_TIME(1504, "출석 가능 시간이 아닙니다." + System.getProperty("line.separator") + "시작 30분 전부터 출석이 가능합니다."),
    NOT_EDU_END_TIME(1505, "종료 가능 시간이 아닙니다." + System.getProperty("line.separator") + "종료 후 1시간 이내 종료가 가능합니다."),
    ALREADY_FINISH_EDU(1506, "이미 종료된 교육입니다." + System.getProperty("line.separator") + "QR 확인 후 다시 요청해 주세요."),
    ALREADY_PROGRESS_EDU(1507, "종료되지 않은 교육이 있습니다." + System.getProperty("line.separator") + "교육 종료 후 요청해 주세요."),
    
    INVALID_EVAL_STATE(1601, "출석을 완료해야 평가가 가능합니다." + System.getProperty("line.separator") + "출석 완료 후 요청해 주세요."),

    NOT_FOUND_PTAXNOTICE(1701, "팜텍스공지 정보를 확인할 수 업습니다. 확인 후 다시 요청해 주세요."),
    NOT_DELETABLE_PTAXNOTICE(1702, "회원에서 PUSH 전송된 공지는 삭제할 수 없습니다."),
    NOT_FOUND_PTAXQNA(1202, "팜텍스 문의 정보를 확인할 수 없습니다. 확인 후 다시 요청해 주세요."),

    ;

    public static VALIDATION valueOfCode(int code) {
        for (VALIDATION value: values()) {
            if (value.code == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("code(" + code + ") is not exist");
    }

    private int code;
    private String message;
}
