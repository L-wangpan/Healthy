package com.healthy.wp.SmallCircle.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan_g_000 on 2016/4/23.
 */
public class Talk {
    public class SBtalk {
        String sbname;
        String words;
        Date time;

        public SBtalk(String sbname, String words) {
            this.sbname = sbname;
            this.words = words;
        }

        public String getSbname() {
            return sbname;
        }

        public void setSbname(String sbname) {
            this.sbname = sbname;
        }

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }

    String contenturl;
    String detile;
    String Name;
    String mypicurl;
    int zannum;
    int talknum;
    List<SBtalk> persons=new ArrayList<SBtalk>();

    public Talk(String name, String detile) {
        Name = name;
        this.detile = detile;
    }

    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }

    public String getDetile() {
        return detile;
    }

    public void setDetile(String detile) {
        this.detile = detile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMypicurl() {
        return mypicurl;
    }

    public void setMypicurl(String mypicurl) {
        this.mypicurl = mypicurl;
    }

    public void addPerson(Talk.SBtalk sb) {
        persons.add(sb);
    }

    public void remove(int i) {
        persons.remove(i);
    }

    public List<SBtalk> getPersons() {
        return this.persons;
    }
}
