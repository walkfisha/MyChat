package util;

import java.io.Serializable;

import static java.security.PublicKey.serialVersionUID;

/**
 * Created by AMOBBS on 2016/11/9.
 */

public class Person implements Serializable{
    private static final long serialVersionUID = 1L;
    public int personId = 0;
    public int personHeadIconId = 0;
    public String personNickNameId = null;
    public String ipAddress = null;
    public String loginTime = null;
    public String timeStamp = null;
    public int groupId = 0;

    public Person(int groupId, String ipAddress, String loginTime, int personHeadIconId, int personId, String personNickNameId, String timeStamp) {
        this.groupId = groupId;
        this.ipAddress = ipAddress;
        this.loginTime = loginTime;
        this.personHeadIconId = personHeadIconId;
        this.personId = personId;
        this.personNickNameId = personNickNameId;
        this.timeStamp = timeStamp;
    }
}

