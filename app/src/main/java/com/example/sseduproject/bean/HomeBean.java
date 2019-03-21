package com.example.sseduproject.bean;

import java.util.List;

public class HomeBean {

    /**
     * banners : []
     * videos : [{"uuid":"e078373c-4f61-485d-9eaa-bec6a9c80861","title":"东北电力大学朝鲜语笔译在线视频课程","desc":null,"pic":"http://192.168.0.80:3000/uploads/learn/video_package/pic/1552188401-49448-0474-4955.jpeg","join_count":0,"quicken":false,"video_count":0,"state":"none","state_text":null,"remind_update":false,"remind_uuid":null},{"uuid":"aec8814e-7845-47fa-9f1d-026c574f38de","title":"吉林大学技术经济及管理在线视频课程","desc":null,"pic":"http://192.168.0.80:3000/uploads/learn/video_package/pic/1552188290-49448-0052-5609.jpeg","join_count":0,"quicken":false,"video_count":0,"state":"none","state_text":null,"remind_update":false,"remind_uuid":null}]
     * papers : [{"uuid":"9f56b965-0b8b-429d-a4b2-8cdf30888e1f","name":"2010年全国硕士研究生入学统一考试历史学试题","question_categories":[{"category":"short","category_text":"简答题","first_position":21,"first_uuid":"4cc0ee6a-2bd6-4f7d-849e-ae9669a4d144"},{"category":"single_select","category_text":"单选题","first_position":1,"first_uuid":"82b1ca9b-8515-4be3-9787-4fd3422e04b7"}]},{"uuid":"a25dbeeb-380c-48b8-b3e0-856a4a12cab0","name":"2016年初级银行从业资格考试《个人贷款》真题精选及解析(二)","question_categories":[{"category":"judge","category_text":"判断题","first_position":131,"first_uuid":"76770f9b-0c2e-49ae-a594-64d0c2c891f6"},{"category":"multiple_select","category_text":"多选题","first_position":91,"first_uuid":"e8052f05-7c2a-4e3c-a3c6-214ae6c2409c"},{"category":"read_carefully","category_text":"阅读理解（仔细阅读）","first_position":1,"first_uuid":"98cb377a-3de1-4616-bfd6-6bedf219a6bf"}]}]
     * buy_paper_package : false
     */

    private boolean buy_paper_package;
    private List<BannerBean> banners;
    private List<VideosBean> videos;
    private List<PapersBean> papers;

    public boolean isBuy_paper_package() {
        return buy_paper_package;
    }

    public void setBuy_paper_package(boolean buy_paper_package) {
        this.buy_paper_package = buy_paper_package;
    }

    public List<BannerBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean> banners) {
        this.banners = banners;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public List<PapersBean> getPapers() {
        return papers;
    }

    public void setPapers(List<PapersBean> papers) {
        this.papers = papers;
    }

    public static class BannerBean {
        private String pic;
        private String title;
        private String url;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "BannerBean{" +
                    "pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class VideosBean {
        /**
         * uuid : e078373c-4f61-485d-9eaa-bec6a9c80861
         * title : 东北电力大学朝鲜语笔译在线视频课程
         * desc : null
         * pic : http://192.168.0.80:3000/uploads/learn/video_package/pic/1552188401-49448-0474-4955.jpeg
         * join_count : 0
         * quicken : false
         * video_count : 0
         * state : none
         * state_text : null
         * remind_update : false
         * remind_uuid : null
         */

        private String uuid;
        private String title;
        private String desc;
        private String pic;
        private String join_count;
        private String quicken;
        private String video_count;
        private String state;
        private String state_text;
        private String remind_update;
        private String remind_uuid;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getJoin_count() {
            return join_count;
        }

        public void setJoin_count(String join_count) {
            this.join_count = join_count;
        }

        public String isQuicken() {
            return quicken;
        }

        public void setQuicken(String quicken) {
            this.quicken = quicken;
        }

        public String getVideo_count() {
            return video_count;
        }

        public void setVideo_count(String video_count) {
            this.video_count = video_count;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Object getState_text() {
            return state_text;
        }

        public void setState_text(String state_text) {
            this.state_text = state_text;
        }

        public String isRemind_update() {
            return remind_update;
        }

        public void setRemind_update(String remind_update) {
            this.remind_update = remind_update;
        }

        public Object getRemind_uuid() {
            return remind_uuid;
        }

        public void setRemind_uuid(String remind_uuid) {
            this.remind_uuid = remind_uuid;
        }

        @Override
        public String toString() {
            return "VideosBean{" +
                    "uuid='" + uuid + '\'' +
                    ", title='" + title + '\'' +
                    ", desc='" + desc + '\'' +
                    ", pic='" + pic + '\'' +
                    ", join_count='" + join_count + '\'' +
                    ", quicken='" + quicken + '\'' +
                    ", video_count='" + video_count + '\'' +
                    ", state='" + state + '\'' +
                    ", state_text='" + state_text + '\'' +
                    ", remind_update='" + remind_update + '\'' +
                    ", remind_uuid='" + remind_uuid + '\'' +
                    '}';
        }
    }

    public static class PapersBean {
        /**
         * uuid : 9f56b965-0b8b-429d-a4b2-8cdf30888e1f
         * name : 2010年全国硕士研究生入学统一考试历史学试题
         * question_categories : [{"category":"short","category_text":"简答题","first_position":21,"first_uuid":"4cc0ee6a-2bd6-4f7d-849e-ae9669a4d144"},{"category":"single_select","category_text":"单选题","first_position":1,"first_uuid":"82b1ca9b-8515-4be3-9787-4fd3422e04b7"}]
         */

        private String uuid;
        private String name;
        private List<QuestionCategoriesBean> question_categories;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<QuestionCategoriesBean> getQuestion_categories() {
            return question_categories;
        }

        public void setQuestion_categories(List<QuestionCategoriesBean> question_categories) {
            this.question_categories = question_categories;
        }

        @Override
        public String toString() {
            return "PapersBean{" +
                    "uuid='" + uuid + '\'' +
                    ", name='" + name + '\'' +
                    ", question_categories=" + question_categories +
                    '}';
        }

        public static class QuestionCategoriesBean {
            /**
             * category : short
             * category_text : 简答题
             * first_position : 21
             * first_uuid : 4cc0ee6a-2bd6-4f7d-849e-ae9669a4d144
             */

            private String category;
            private String category_text;
            private String first_position;
            private String first_uuid;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCategory_text() {
                return category_text;
            }

            public void setCategory_text(String category_text) {
                this.category_text = category_text;
            }

            public String getFirst_position() {
                return first_position;
            }

            public void setFirst_position(String first_position) {
                this.first_position = first_position;
            }

            public String getFirst_uuid() {
                return first_uuid;
            }

            public void setFirst_uuid(String first_uuid) {
                this.first_uuid = first_uuid;
            }

            @Override
            public String toString() {
                return "QuestionCategoriesBean{" +
                        "category='" + category + '\'' +
                        ", category_text='" + category_text + '\'' +
                        ", first_position='" + first_position + '\'' +
                        ", first_uuid='" + first_uuid + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "buy_paper_package=" + buy_paper_package +
                ", banners=" + banners +
                ", videos=" + videos +
                ", papers=" + papers +
                '}';
    }
}
