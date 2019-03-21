package com.example.sseduproject.bean;

import java.util.List;

public class UserInfoBean {


    /**
     * uuid : e55a56ee-850a-4b53-83f4-786a48de131e
     * nickname :
     * gender : m
     * avatar : {"url":"http://192.168.0.80:3000/uploads/user/avatar/133/133.jpeg","thumb":{"url":"http://192.168.0.80:3000/uploads/user/avatar/133/thumb_133.jpeg"}}
     * school : 北京大学
     * profession : (专业学位)电子与通信工程
     * school_year : 2016
     * demand_tags : ["考研"]
     * target_school_professions : [{"school_code":"13","school_name":"长春大学","profession_id":870,"profession_name":"会计学"}]
     * phone : 17386830712
     */

    private String uuid;
    private String nickname;
    private String gender;
    private AvatarBean avatar;
    private String school;
    private String profession;
    private String school_year;
    private String phone;
    private List<String> demand_tags;
    private List<TargetSchoolProfessionsBean> target_school_professions;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AvatarBean getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarBean avatar) {
        this.avatar = avatar;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSchool_year() {
        return school_year;
    }

    public void setSchool_year(String school_year) {
        this.school_year = school_year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getDemand_tags() {
        return demand_tags;
    }

    public void setDemand_tags(List<String> demand_tags) {
        this.demand_tags = demand_tags;
    }

    public List<TargetSchoolProfessionsBean> getTarget_school_professions() {
        return target_school_professions;
    }

    public void setTarget_school_professions(List<TargetSchoolProfessionsBean> target_school_professions) {
        this.target_school_professions = target_school_professions;
    }

    public static class AvatarBean {
        /**
         * url : http://192.168.0.80:3000/uploads/user/avatar/133/133.jpeg
         * thumb : {"url":"http://192.168.0.80:3000/uploads/user/avatar/133/thumb_133.jpeg"}
         */

        private String url;
        private ThumbBean thumb;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public ThumbBean getThumb() {
            return thumb;
        }

        public void setThumb(ThumbBean thumb) {
            this.thumb = thumb;
        }

        public static class ThumbBean {
            /**
             * url : http://192.168.0.80:3000/uploads/user/avatar/133/thumb_133.jpeg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class TargetSchoolProfessionsBean {
        /**
         * school_code : 13
         * school_name : 长春大学
         * profession_id : 870
         * profession_name : 会计学
         */

        private String school_code;
        private String school_name;
        private int profession_id;
        private String profession_name;

        public String getSchool_code() {
            return school_code;
        }

        public void setSchool_code(String school_code) {
            this.school_code = school_code;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }

        public int getProfession_id() {
            return profession_id;
        }

        public void setProfession_id(int profession_id) {
            this.profession_id = profession_id;
        }

        public String getProfession_name() {
            return profession_name;
        }

        public void setProfession_name(String profession_name) {
            this.profession_name = profession_name;
        }
    }
}
