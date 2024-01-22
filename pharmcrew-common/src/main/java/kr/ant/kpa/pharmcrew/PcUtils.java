package kr.ant.kpa.pharmcrew;

import com.bumdori.util.FileUtils;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;

@Component
public class PcUtils {
    public final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    public final SimpleDateFormat dateHFormat = new SimpleDateFormat("yyyy.MM.dd-HH");
    public final SimpleDateFormat dateHMFormat = new SimpleDateFormat("yyyy.MM.dd-HH-mm");

    public File save(MultipartFile multipartFile) throws FailSaveFileException {
        try {
            return FileUtils.save(multipartFile, true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailSaveFileException();
        }
    }

    public String encryptPassword(String plain) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plain.getBytes());
            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
