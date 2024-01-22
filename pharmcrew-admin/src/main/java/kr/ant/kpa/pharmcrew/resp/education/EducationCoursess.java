package kr.ant.kpa.pharmcrew.resp.education;

import com.bumdori.spring.annotation.Description;
import lombok.Data;

import java.util.List;

@Data
public class EducationCoursess {
    /**
     * 강의 리스트
     */
    @Description("강의 리스트")
    private List<List<EducationCourse>> coursess;
}
