package kr.ant.kpa.pharmcrew;

import kr.ant.kpa.pharmcrew.config.DbConfig;
import kr.ant.kpa.pharmcrew.config.RedisConfig;

public enum Environment {
    DHCHOI_1 {
        @Override
        boolean check(String user, String os, String host, String ip, String tmpDir) {
            return "DESKTOP-MI0O1ID".equals(host);
//            return "DESKTOP-J3GJ2GR".equals(host);
        }

        @Override
        String localStoragePath() {
//            return "C:/Users/dhchoi/AppData/Local/Packages/CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc/LocalState/rootfs/var/www/html/pharmcrew/";
            return "E:/localstorage/pharmcrew/";
        }

        @Override
        String localStorageUrl() {
//            return "http://localhost/pharmcrew/";
            return "http://localhost:8088/pharmcrew/";
        }

    },
    DHCHOI_2 {
        @Override
        boolean check(String user, String os, String host, String ip, String tmpDir) {
            return "choedonghyeog-ui-iMac.local".equals(host);
        }

        @Override
        String localStoragePath() {
            return "/Users/dhchoi/www/pharmcrew/";
        }

        @Override
        String localStorageUrl() {
            return "http://localhost/pharmcrew/";
        }
    },
    BEKSUNG {
        @Override
        boolean check(String user, String os, String host, String ip, String tmpDir) {
            return "Beksungui-MacBookPro.local".equals(host);
        }

        @Override
        String localStoragePath() {
            return "/Library/WebServer/Documents/PharmCrew/";
        }

        @Override
        String localStorageUrl() {
            return "http://localhost/PharmCrew/";
        }
    },
 	CAFE24_SERVER {
    	 @Override
         boolean check(String user, String os, String host, String ip, String tmpDir) {
    		 return host.equals("amytech.cafe24.com");
         }

         @Override
         String localStoragePath() {
             return "/var/www/html/PharmCrew/";
         }

         @Override
         String localStorageUrl() {
             return "http://amytech.cafe24.com/PharmCrew/";
         }        
 	},
    PROD {
        @Override
        boolean check(String user, String os, String host, String ip, String tmpDir) {
            return "ip-10-0-2-24.ap-northeast-2.compute.internal".equals(host);
        }

        @Override
        String localStoragePath() {
            return "/pharmtax/pharm-crew/upload-files/";
        }

        @Override
        String localStorageUrl() {
            return "https://pc-api.e-billing.co.kr/boardfile/";
        }
        
        // db
        @Override
        DbConfig.DbServer dbServer() {
        	return DbConfig.DbServer.PROD;
        }
        
        // redis
        @Override
        RedisConfig.RedisServer redisServer() {
            return RedisConfig.RedisServer.PROD;
        }
        @Override
        RedisConfig.RedisServer redisForApiSessionServer() {
            return RedisConfig.RedisServer.PROD_API_SESSION;
        }
        @Override
        RedisConfig.RedisServer redisForAdminSessionServer() {
            return RedisConfig.RedisServer.PROD_ADMIN_SESSION;
        }
    }
    ;

    abstract boolean check(String user, String os, String host, String ip, String tmpDir);

    // db
    DbConfig.DbServer dbServer() {
        return DbConfig.DbServer.AMYS;
    }

    // redis
    RedisConfig.RedisServer redisServer() {
        return RedisConfig.RedisServer.AMYS;
    }
    RedisConfig.RedisServer redisForApiSessionServer() {
        return RedisConfig.RedisServer.AMYS_API_SESSION;
    }
    RedisConfig.RedisServer redisForAdminSessionServer() {
        return RedisConfig.RedisServer.AMYS_ADMIN_SESSION;
    }

    // storage
    abstract String localStoragePath();
    abstract String localStorageUrl();
}
