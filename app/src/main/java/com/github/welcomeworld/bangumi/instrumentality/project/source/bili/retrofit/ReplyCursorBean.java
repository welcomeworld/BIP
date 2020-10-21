package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit;

import java.util.List;

public class ReplyCursorBean {


    private int code;
    private String message;
    private int ttl;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int assist;
        private int blacklist;
        private ConfigBean config;
        private CursorBean cursor;
        private NoticeBean notice;
        private TopBean top;
        private UpperBeanX upper;
        private int vote;
        private List<HotsBean> hots;
        private List<RepliesBeanXX> replies;

        public int getAssist() {
            return assist;
        }

        public void setAssist(int assist) {
            this.assist = assist;
        }

        public int getBlacklist() {
            return blacklist;
        }

        public void setBlacklist(int blacklist) {
            this.blacklist = blacklist;
        }

        public ConfigBean getConfig() {
            return config;
        }

        public void setConfig(ConfigBean config) {
            this.config = config;
        }

        public CursorBean getCursor() {
            return cursor;
        }

        public void setCursor(CursorBean cursor) {
            this.cursor = cursor;
        }

        public Object getNotice() {
            return notice;
        }

        public void setNotice(NoticeBean notice) {
            this.notice = notice;
        }

        public TopBean getTop() {
            return top;
        }

        public void setTop(TopBean top) {
            this.top = top;
        }

        public UpperBeanX getUpper() {
            return upper;
        }

        public void setUpper(UpperBeanX upper) {
            this.upper = upper;
        }

        public int getVote() {
            return vote;
        }

        public void setVote(int vote) {
            this.vote = vote;
        }

        public List<HotsBean> getHots() {
            return hots;
        }

        public void setHots(List<HotsBean> hots) {
            this.hots = hots;
        }

        public List<RepliesBeanXX> getReplies() {
            return replies;
        }

        public void setReplies(List<RepliesBeanXX> replies) {
            this.replies = replies;
        }

        public static class ConfigBean {
            private int showadmin;
            private int showentry;
            private int showfloor;
            private int showtopic;

            public int getShowadmin() {
                return showadmin;
            }

            public void setShowadmin(int showadmin) {
                this.showadmin = showadmin;
            }

            public int getShowentry() {
                return showentry;
            }

            public void setShowentry(int showentry) {
                this.showentry = showentry;
            }

            public int getShowfloor() {
                return showfloor;
            }

            public void setShowfloor(int showfloor) {
                this.showfloor = showfloor;
            }

            public int getShowtopic() {
                return showtopic;
            }

            public void setShowtopic(int showtopic) {
                this.showtopic = showtopic;
            }
        }

        public static class CursorBean {
            private int all_count;
            private int max_id;
            private int min_id;
            private int size;

            public int getAll_count() {
                return all_count;
            }

            public void setAll_count(int all_count) {
                this.all_count = all_count;
            }

            public int getMax_id() {
                return max_id;
            }

            public void setMax_id(int max_id) {
                this.max_id = max_id;
            }

            public int getMin_id() {
                return min_id;
            }

            public void setMin_id(int min_id) {
                this.min_id = min_id;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }

        public static class NoticeBean {
            private int id;
            private String title;
            private String content;
            private String link;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class TopBean {
            private Object admin;
            private UpperBean upper;
            private Object vote;

            public Object getAdmin() {
                return admin;
            }

            public void setAdmin(Object admin) {
                this.admin = admin;
            }

            public UpperBean getUpper() {
                return upper;
            }

            public void setUpper(UpperBean upper) {
                this.upper = upper;
            }

            public Object getVote() {
                return vote;
            }

            public void setVote(Object vote) {
                this.vote = vote;
            }

            public static class UpperBean {
                private int rpid;
                private int oid;
                private int type;
                private int mid;
                private int root;
                private int parent;
                private int dialog;
                private int count;
                private int rcount;
                private int floor;
                private int state;
                private int fansgrade;
                private int attr;
                private int ctime;
                private String rpid_str;
                private String root_str;
                private String parent_str;
                private String dialog_str;
                private int like;
                private int action;
                private MemberBean member;
                private ContentBean content;
                private int assist;
                private FolderBean folder;
                private UpActionBean up_action;
                private List<RepliesBean> replies;

                public int getRpid() {
                    return rpid;
                }

                public void setRpid(int rpid) {
                    this.rpid = rpid;
                }

                public int getOid() {
                    return oid;
                }

                public void setOid(int oid) {
                    this.oid = oid;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getMid() {
                    return mid;
                }

                public void setMid(int mid) {
                    this.mid = mid;
                }

                public int getRoot() {
                    return root;
                }

                public void setRoot(int root) {
                    this.root = root;
                }

                public int getParent() {
                    return parent;
                }

                public void setParent(int parent) {
                    this.parent = parent;
                }

                public int getDialog() {
                    return dialog;
                }

                public void setDialog(int dialog) {
                    this.dialog = dialog;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public int getRcount() {
                    return rcount;
                }

                public void setRcount(int rcount) {
                    this.rcount = rcount;
                }

                public int getFloor() {
                    return floor;
                }

                public void setFloor(int floor) {
                    this.floor = floor;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public int getFansgrade() {
                    return fansgrade;
                }

                public void setFansgrade(int fansgrade) {
                    this.fansgrade = fansgrade;
                }

                public int getAttr() {
                    return attr;
                }

                public void setAttr(int attr) {
                    this.attr = attr;
                }

                public int getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
                    this.ctime = ctime;
                }

                public String getRpid_str() {
                    return rpid_str;
                }

                public void setRpid_str(String rpid_str) {
                    this.rpid_str = rpid_str;
                }

                public String getRoot_str() {
                    return root_str;
                }

                public void setRoot_str(String root_str) {
                    this.root_str = root_str;
                }

                public String getParent_str() {
                    return parent_str;
                }

                public void setParent_str(String parent_str) {
                    this.parent_str = parent_str;
                }

                public String getDialog_str() {
                    return dialog_str;
                }

                public void setDialog_str(String dialog_str) {
                    this.dialog_str = dialog_str;
                }

                public int getLike() {
                    return like;
                }

                public void setLike(int like) {
                    this.like = like;
                }

                public int getAction() {
                    return action;
                }

                public void setAction(int action) {
                    this.action = action;
                }

                public MemberBean getMember() {
                    return member;
                }

                public void setMember(MemberBean member) {
                    this.member = member;
                }

                public ContentBean getContent() {
                    return content;
                }

                public void setContent(ContentBean content) {
                    this.content = content;
                }

                public int getAssist() {
                    return assist;
                }

                public void setAssist(int assist) {
                    this.assist = assist;
                }

                public FolderBean getFolder() {
                    return folder;
                }

                public void setFolder(FolderBean folder) {
                    this.folder = folder;
                }

                public UpActionBean getUp_action() {
                    return up_action;
                }

                public void setUp_action(UpActionBean up_action) {
                    this.up_action = up_action;
                }

                public List<RepliesBean> getReplies() {
                    return replies;
                }

                public void setReplies(List<RepliesBean> replies) {
                    this.replies = replies;
                }

                public static class MemberBean {
                    private String mid;
                    private String uname;
                    private String sex;
                    private String sign;
                    private String avatar;
                    private String rank;
                    private String DisplayRank;
                    private LevelInfoBean level_info;
                    private PendantBean pendant;
                    private NameplateBean nameplate;
                    private OfficialVerifyBean official_verify;
                    private VipBean vip;
                    private Object fans_detail;
                    private int following;

                    public String getMid() {
                        return mid;
                    }

                    public void setMid(String mid) {
                        this.mid = mid;
                    }

                    public String getUname() {
                        return uname;
                    }

                    public void setUname(String uname) {
                        this.uname = uname;
                    }

                    public String getSex() {
                        return sex;
                    }

                    public void setSex(String sex) {
                        this.sex = sex;
                    }

                    public String getSign() {
                        return sign;
                    }

                    public void setSign(String sign) {
                        this.sign = sign;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getRank() {
                        return rank;
                    }

                    public void setRank(String rank) {
                        this.rank = rank;
                    }

                    public String getDisplayRank() {
                        return DisplayRank;
                    }

                    public void setDisplayRank(String DisplayRank) {
                        this.DisplayRank = DisplayRank;
                    }

                    public LevelInfoBean getLevel_info() {
                        return level_info;
                    }

                    public void setLevel_info(LevelInfoBean level_info) {
                        this.level_info = level_info;
                    }

                    public PendantBean getPendant() {
                        return pendant;
                    }

                    public void setPendant(PendantBean pendant) {
                        this.pendant = pendant;
                    }

                    public NameplateBean getNameplate() {
                        return nameplate;
                    }

                    public void setNameplate(NameplateBean nameplate) {
                        this.nameplate = nameplate;
                    }

                    public OfficialVerifyBean getOfficial_verify() {
                        return official_verify;
                    }

                    public void setOfficial_verify(OfficialVerifyBean official_verify) {
                        this.official_verify = official_verify;
                    }

                    public VipBean getVip() {
                        return vip;
                    }

                    public void setVip(VipBean vip) {
                        this.vip = vip;
                    }

                    public Object getFans_detail() {
                        return fans_detail;
                    }

                    public void setFans_detail(Object fans_detail) {
                        this.fans_detail = fans_detail;
                    }

                    public int getFollowing() {
                        return following;
                    }

                    public void setFollowing(int following) {
                        this.following = following;
                    }

                    public static class LevelInfoBean {
                        private int current_level;
                        private int current_min;
                        private int current_exp;
                        private int next_exp;

                        public int getCurrent_level() {
                            return current_level;
                        }

                        public void setCurrent_level(int current_level) {
                            this.current_level = current_level;
                        }

                        public int getCurrent_min() {
                            return current_min;
                        }

                        public void setCurrent_min(int current_min) {
                            this.current_min = current_min;
                        }

                        public int getCurrent_exp() {
                            return current_exp;
                        }

                        public void setCurrent_exp(int current_exp) {
                            this.current_exp = current_exp;
                        }

                        public int getNext_exp() {
                            return next_exp;
                        }

                        public void setNext_exp(int next_exp) {
                            this.next_exp = next_exp;
                        }
                    }

                    public static class PendantBean {
                        private int pid;
                        private String name;
                        private String image;
                        private int expire;

                        public int getPid() {
                            return pid;
                        }

                        public void setPid(int pid) {
                            this.pid = pid;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getImage() {
                            return image;
                        }

                        public void setImage(String image) {
                            this.image = image;
                        }

                        public int getExpire() {
                            return expire;
                        }

                        public void setExpire(int expire) {
                            this.expire = expire;
                        }
                    }

                    public static class NameplateBean {
                        private int nid;
                        private String name;
                        private String image;
                        private String image_small;
                        private String level;
                        private String condition;

                        public int getNid() {
                            return nid;
                        }

                        public void setNid(int nid) {
                            this.nid = nid;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getImage() {
                            return image;
                        }

                        public void setImage(String image) {
                            this.image = image;
                        }

                        public String getImage_small() {
                            return image_small;
                        }

                        public void setImage_small(String image_small) {
                            this.image_small = image_small;
                        }

                        public String getLevel() {
                            return level;
                        }

                        public void setLevel(String level) {
                            this.level = level;
                        }

                        public String getCondition() {
                            return condition;
                        }

                        public void setCondition(String condition) {
                            this.condition = condition;
                        }
                    }

                    public static class OfficialVerifyBean {
                        private int type;
                        private String desc;

                        public int getType() {
                            return type;
                        }

                        public void setType(int type) {
                            this.type = type;
                        }

                        public String getDesc() {
                            return desc;
                        }

                        public void setDesc(String desc) {
                            this.desc = desc;
                        }
                    }

                    public static class VipBean {
                        private int vipType;
                        private long vipDueDate;
                        private String dueRemark;
                        private int accessStatus;
                        private int vipStatus;
                        private String vipStatusWarn;

                        public int getVipType() {
                            return vipType;
                        }

                        public void setVipType(int vipType) {
                            this.vipType = vipType;
                        }

                        public long getVipDueDate() {
                            return vipDueDate;
                        }

                        public void setVipDueDate(long vipDueDate) {
                            this.vipDueDate = vipDueDate;
                        }

                        public String getDueRemark() {
                            return dueRemark;
                        }

                        public void setDueRemark(String dueRemark) {
                            this.dueRemark = dueRemark;
                        }

                        public int getAccessStatus() {
                            return accessStatus;
                        }

                        public void setAccessStatus(int accessStatus) {
                            this.accessStatus = accessStatus;
                        }

                        public int getVipStatus() {
                            return vipStatus;
                        }

                        public void setVipStatus(int vipStatus) {
                            this.vipStatus = vipStatus;
                        }

                        public String getVipStatusWarn() {
                            return vipStatusWarn;
                        }

                        public void setVipStatusWarn(String vipStatusWarn) {
                            this.vipStatusWarn = vipStatusWarn;
                        }
                    }
                }

                public static class ContentBean {
                    private String message;
                    private int plat;
                    private String device;
                    private List<?> members;

                    public String getMessage() {
                        return message;
                    }

                    public void setMessage(String message) {
                        this.message = message;
                    }

                    public int getPlat() {
                        return plat;
                    }

                    public void setPlat(int plat) {
                        this.plat = plat;
                    }

                    public String getDevice() {
                        return device;
                    }

                    public void setDevice(String device) {
                        this.device = device;
                    }

                    public List<?> getMembers() {
                        return members;
                    }

                    public void setMembers(List<?> members) {
                        this.members = members;
                    }
                }

                public static class FolderBean {
                    private boolean has_folded;
                    private boolean is_folded;
                    private String rule;

                    public boolean isHas_folded() {
                        return has_folded;
                    }

                    public void setHas_folded(boolean has_folded) {
                        this.has_folded = has_folded;
                    }

                    public boolean isIs_folded() {
                        return is_folded;
                    }

                    public void setIs_folded(boolean is_folded) {
                        this.is_folded = is_folded;
                    }

                    public String getRule() {
                        return rule;
                    }

                    public void setRule(String rule) {
                        this.rule = rule;
                    }
                }

                public static class UpActionBean {
                    private boolean like;
                    private boolean reply;

                    public boolean isLike() {
                        return like;
                    }

                    public void setLike(boolean like) {
                        this.like = like;
                    }

                    public boolean isReply() {
                        return reply;
                    }

                    public void setReply(boolean reply) {
                        this.reply = reply;
                    }
                }

                public static class RepliesBean {
                    private int rpid;
                    private int oid;
                    private int type;
                    private int mid;
                    private int root;
                    private int parent;
                    private int dialog;
                    private int count;
                    private int rcount;
                    private int floor;
                    private int state;
                    private int fansgrade;
                    private int attr;
                    private int ctime;
                    private String rpid_str;
                    private String root_str;
                    private String parent_str;
                    private String dialog_str;
                    private int like;
                    private int action;
                    private MemberBeanX member;
                    private ContentBeanX content;
                    private int assist;
                    private FolderBeanX folder;
                    private UpActionBeanX up_action;
                    private List<?> replies;

                    public int getRpid() {
                        return rpid;
                    }

                    public void setRpid(int rpid) {
                        this.rpid = rpid;
                    }

                    public int getOid() {
                        return oid;
                    }

                    public void setOid(int oid) {
                        this.oid = oid;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public int getMid() {
                        return mid;
                    }

                    public void setMid(int mid) {
                        this.mid = mid;
                    }

                    public int getRoot() {
                        return root;
                    }

                    public void setRoot(int root) {
                        this.root = root;
                    }

                    public int getParent() {
                        return parent;
                    }

                    public void setParent(int parent) {
                        this.parent = parent;
                    }

                    public int getDialog() {
                        return dialog;
                    }

                    public void setDialog(int dialog) {
                        this.dialog = dialog;
                    }

                    public int getCount() {
                        return count;
                    }

                    public void setCount(int count) {
                        this.count = count;
                    }

                    public int getRcount() {
                        return rcount;
                    }

                    public void setRcount(int rcount) {
                        this.rcount = rcount;
                    }

                    public int getFloor() {
                        return floor;
                    }

                    public void setFloor(int floor) {
                        this.floor = floor;
                    }

                    public int getState() {
                        return state;
                    }

                    public void setState(int state) {
                        this.state = state;
                    }

                    public int getFansgrade() {
                        return fansgrade;
                    }

                    public void setFansgrade(int fansgrade) {
                        this.fansgrade = fansgrade;
                    }

                    public int getAttr() {
                        return attr;
                    }

                    public void setAttr(int attr) {
                        this.attr = attr;
                    }

                    public int getCtime() {
                        return ctime;
                    }

                    public void setCtime(int ctime) {
                        this.ctime = ctime;
                    }

                    public String getRpid_str() {
                        return rpid_str;
                    }

                    public void setRpid_str(String rpid_str) {
                        this.rpid_str = rpid_str;
                    }

                    public String getRoot_str() {
                        return root_str;
                    }

                    public void setRoot_str(String root_str) {
                        this.root_str = root_str;
                    }

                    public String getParent_str() {
                        return parent_str;
                    }

                    public void setParent_str(String parent_str) {
                        this.parent_str = parent_str;
                    }

                    public String getDialog_str() {
                        return dialog_str;
                    }

                    public void setDialog_str(String dialog_str) {
                        this.dialog_str = dialog_str;
                    }

                    public int getLike() {
                        return like;
                    }

                    public void setLike(int like) {
                        this.like = like;
                    }

                    public int getAction() {
                        return action;
                    }

                    public void setAction(int action) {
                        this.action = action;
                    }

                    public MemberBeanX getMember() {
                        return member;
                    }

                    public void setMember(MemberBeanX member) {
                        this.member = member;
                    }

                    public ContentBeanX getContent() {
                        return content;
                    }

                    public void setContent(ContentBeanX content) {
                        this.content = content;
                    }

                    public int getAssist() {
                        return assist;
                    }

                    public void setAssist(int assist) {
                        this.assist = assist;
                    }

                    public FolderBeanX getFolder() {
                        return folder;
                    }

                    public void setFolder(FolderBeanX folder) {
                        this.folder = folder;
                    }

                    public UpActionBeanX getUp_action() {
                        return up_action;
                    }

                    public void setUp_action(UpActionBeanX up_action) {
                        this.up_action = up_action;
                    }

                    public List<?> getReplies() {
                        return replies;
                    }

                    public void setReplies(List<?> replies) {
                        this.replies = replies;
                    }

                    public static class MemberBeanX {
                        private String mid;
                        private String uname;
                        private String sex;
                        private String sign;
                        private String avatar;
                        private String rank;
                        private String DisplayRank;
                        private LevelInfoBeanX level_info;
                        private PendantBeanX pendant;
                        private NameplateBeanX nameplate;
                        private OfficialVerifyBeanX official_verify;
                        private VipBeanX vip;
                        private Object fans_detail;
                        private int following;

                        public String getMid() {
                            return mid;
                        }

                        public void setMid(String mid) {
                            this.mid = mid;
                        }

                        public String getUname() {
                            return uname;
                        }

                        public void setUname(String uname) {
                            this.uname = uname;
                        }

                        public String getSex() {
                            return sex;
                        }

                        public void setSex(String sex) {
                            this.sex = sex;
                        }

                        public String getSign() {
                            return sign;
                        }

                        public void setSign(String sign) {
                            this.sign = sign;
                        }

                        public String getAvatar() {
                            return avatar;
                        }

                        public void setAvatar(String avatar) {
                            this.avatar = avatar;
                        }

                        public String getRank() {
                            return rank;
                        }

                        public void setRank(String rank) {
                            this.rank = rank;
                        }

                        public String getDisplayRank() {
                            return DisplayRank;
                        }

                        public void setDisplayRank(String DisplayRank) {
                            this.DisplayRank = DisplayRank;
                        }

                        public LevelInfoBeanX getLevel_info() {
                            return level_info;
                        }

                        public void setLevel_info(LevelInfoBeanX level_info) {
                            this.level_info = level_info;
                        }

                        public PendantBeanX getPendant() {
                            return pendant;
                        }

                        public void setPendant(PendantBeanX pendant) {
                            this.pendant = pendant;
                        }

                        public NameplateBeanX getNameplate() {
                            return nameplate;
                        }

                        public void setNameplate(NameplateBeanX nameplate) {
                            this.nameplate = nameplate;
                        }

                        public OfficialVerifyBeanX getOfficial_verify() {
                            return official_verify;
                        }

                        public void setOfficial_verify(OfficialVerifyBeanX official_verify) {
                            this.official_verify = official_verify;
                        }

                        public VipBeanX getVip() {
                            return vip;
                        }

                        public void setVip(VipBeanX vip) {
                            this.vip = vip;
                        }

                        public Object getFans_detail() {
                            return fans_detail;
                        }

                        public void setFans_detail(Object fans_detail) {
                            this.fans_detail = fans_detail;
                        }

                        public int getFollowing() {
                            return following;
                        }

                        public void setFollowing(int following) {
                            this.following = following;
                        }

                        public static class LevelInfoBeanX {
                            private int current_level;
                            private int current_min;
                            private int current_exp;
                            private int next_exp;

                            public int getCurrent_level() {
                                return current_level;
                            }

                            public void setCurrent_level(int current_level) {
                                this.current_level = current_level;
                            }

                            public int getCurrent_min() {
                                return current_min;
                            }

                            public void setCurrent_min(int current_min) {
                                this.current_min = current_min;
                            }

                            public int getCurrent_exp() {
                                return current_exp;
                            }

                            public void setCurrent_exp(int current_exp) {
                                this.current_exp = current_exp;
                            }

                            public int getNext_exp() {
                                return next_exp;
                            }

                            public void setNext_exp(int next_exp) {
                                this.next_exp = next_exp;
                            }
                        }

                        public static class PendantBeanX {
                            private int pid;
                            private String name;
                            private String image;
                            private int expire;

                            public int getPid() {
                                return pid;
                            }

                            public void setPid(int pid) {
                                this.pid = pid;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getImage() {
                                return image;
                            }

                            public void setImage(String image) {
                                this.image = image;
                            }

                            public int getExpire() {
                                return expire;
                            }

                            public void setExpire(int expire) {
                                this.expire = expire;
                            }
                        }

                        public static class NameplateBeanX {
                            private int nid;
                            private String name;
                            private String image;
                            private String image_small;
                            private String level;
                            private String condition;

                            public int getNid() {
                                return nid;
                            }

                            public void setNid(int nid) {
                                this.nid = nid;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getImage() {
                                return image;
                            }

                            public void setImage(String image) {
                                this.image = image;
                            }

                            public String getImage_small() {
                                return image_small;
                            }

                            public void setImage_small(String image_small) {
                                this.image_small = image_small;
                            }

                            public String getLevel() {
                                return level;
                            }

                            public void setLevel(String level) {
                                this.level = level;
                            }

                            public String getCondition() {
                                return condition;
                            }

                            public void setCondition(String condition) {
                                this.condition = condition;
                            }
                        }

                        public static class OfficialVerifyBeanX {
                            private int type;
                            private String desc;

                            public int getType() {
                                return type;
                            }

                            public void setType(int type) {
                                this.type = type;
                            }

                            public String getDesc() {
                                return desc;
                            }

                            public void setDesc(String desc) {
                                this.desc = desc;
                            }
                        }

                        public static class VipBeanX {
                            private int vipType;
                            private long vipDueDate;
                            private String dueRemark;
                            private int accessStatus;
                            private int vipStatus;
                            private String vipStatusWarn;

                            public int getVipType() {
                                return vipType;
                            }

                            public void setVipType(int vipType) {
                                this.vipType = vipType;
                            }

                            public long getVipDueDate() {
                                return vipDueDate;
                            }

                            public void setVipDueDate(long vipDueDate) {
                                this.vipDueDate = vipDueDate;
                            }

                            public String getDueRemark() {
                                return dueRemark;
                            }

                            public void setDueRemark(String dueRemark) {
                                this.dueRemark = dueRemark;
                            }

                            public int getAccessStatus() {
                                return accessStatus;
                            }

                            public void setAccessStatus(int accessStatus) {
                                this.accessStatus = accessStatus;
                            }

                            public int getVipStatus() {
                                return vipStatus;
                            }

                            public void setVipStatus(int vipStatus) {
                                this.vipStatus = vipStatus;
                            }

                            public String getVipStatusWarn() {
                                return vipStatusWarn;
                            }

                            public void setVipStatusWarn(String vipStatusWarn) {
                                this.vipStatusWarn = vipStatusWarn;
                            }
                        }
                    }

                    public static class ContentBeanX {
                        private String message;
                        private int plat;
                        private String device;
                        private List<?> members;

                        public String getMessage() {
                            return message;
                        }

                        public void setMessage(String message) {
                            this.message = message;
                        }

                        public int getPlat() {
                            return plat;
                        }

                        public void setPlat(int plat) {
                            this.plat = plat;
                        }

                        public String getDevice() {
                            return device;
                        }

                        public void setDevice(String device) {
                            this.device = device;
                        }

                        public List<?> getMembers() {
                            return members;
                        }

                        public void setMembers(List<?> members) {
                            this.members = members;
                        }
                    }

                    public static class FolderBeanX {
                        private boolean has_folded;
                        private boolean is_folded;
                        private String rule;

                        public boolean isHas_folded() {
                            return has_folded;
                        }

                        public void setHas_folded(boolean has_folded) {
                            this.has_folded = has_folded;
                        }

                        public boolean isIs_folded() {
                            return is_folded;
                        }

                        public void setIs_folded(boolean is_folded) {
                            this.is_folded = is_folded;
                        }

                        public String getRule() {
                            return rule;
                        }

                        public void setRule(String rule) {
                            this.rule = rule;
                        }
                    }

                    public static class UpActionBeanX {
                        private boolean like;
                        private boolean reply;

                        public boolean isLike() {
                            return like;
                        }

                        public void setLike(boolean like) {
                            this.like = like;
                        }

                        public boolean isReply() {
                            return reply;
                        }

                        public void setReply(boolean reply) {
                            this.reply = reply;
                        }
                    }
                }
            }
        }

        public static class UpperBeanX {
            private int mid;

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }
        }

        public static class HotsBean {
            private int rpid;
            private int oid;
            private int type;
            private int mid;
            private int root;
            private int parent;
            private int dialog;
            private int count;
            private int rcount;
            private int floor;
            private int state;
            private int fansgrade;
            private int attr;
            private int ctime;
            private String rpid_str;
            private String root_str;
            private String parent_str;
            private String dialog_str;
            private int like;
            private int action;
            private MemberBeanXX member;
            private ContentBeanXX content;
            private int assist;
            private FolderBeanXX folder;
            private UpActionBeanXX up_action;
            private List<RepliesBeanX> replies;

            public int getRpid() {
                return rpid;
            }

            public void setRpid(int rpid) {
                this.rpid = rpid;
            }

            public int getOid() {
                return oid;
            }

            public void setOid(int oid) {
                this.oid = oid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public int getRoot() {
                return root;
            }

            public void setRoot(int root) {
                this.root = root;
            }

            public int getParent() {
                return parent;
            }

            public void setParent(int parent) {
                this.parent = parent;
            }

            public int getDialog() {
                return dialog;
            }

            public void setDialog(int dialog) {
                this.dialog = dialog;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getRcount() {
                return rcount;
            }

            public void setRcount(int rcount) {
                this.rcount = rcount;
            }

            public int getFloor() {
                return floor;
            }

            public void setFloor(int floor) {
                this.floor = floor;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getFansgrade() {
                return fansgrade;
            }

            public void setFansgrade(int fansgrade) {
                this.fansgrade = fansgrade;
            }

            public int getAttr() {
                return attr;
            }

            public void setAttr(int attr) {
                this.attr = attr;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public String getRpid_str() {
                return rpid_str;
            }

            public void setRpid_str(String rpid_str) {
                this.rpid_str = rpid_str;
            }

            public String getRoot_str() {
                return root_str;
            }

            public void setRoot_str(String root_str) {
                this.root_str = root_str;
            }

            public String getParent_str() {
                return parent_str;
            }

            public void setParent_str(String parent_str) {
                this.parent_str = parent_str;
            }

            public String getDialog_str() {
                return dialog_str;
            }

            public void setDialog_str(String dialog_str) {
                this.dialog_str = dialog_str;
            }

            public int getLike() {
                return like;
            }

            public void setLike(int like) {
                this.like = like;
            }

            public int getAction() {
                return action;
            }

            public void setAction(int action) {
                this.action = action;
            }

            public MemberBeanXX getMember() {
                return member;
            }

            public void setMember(MemberBeanXX member) {
                this.member = member;
            }

            public ContentBeanXX getContent() {
                return content;
            }

            public void setContent(ContentBeanXX content) {
                this.content = content;
            }

            public int getAssist() {
                return assist;
            }

            public void setAssist(int assist) {
                this.assist = assist;
            }

            public FolderBeanXX getFolder() {
                return folder;
            }

            public void setFolder(FolderBeanXX folder) {
                this.folder = folder;
            }

            public UpActionBeanXX getUp_action() {
                return up_action;
            }

            public void setUp_action(UpActionBeanXX up_action) {
                this.up_action = up_action;
            }

            public List<RepliesBeanX> getReplies() {
                return replies;
            }

            public void setReplies(List<RepliesBeanX> replies) {
                this.replies = replies;
            }

            public static class MemberBeanXX {
                private String mid;
                private String uname;
                private String sex;
                private String sign;
                private String avatar;
                private String rank;
                private String DisplayRank;
                private LevelInfoBeanXX level_info;
                private PendantBeanXX pendant;
                private NameplateBeanXX nameplate;
                private OfficialVerifyBeanXX official_verify;
                private VipBeanXX vip;
                private Object fans_detail;
                private int following;

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getRank() {
                    return rank;
                }

                public void setRank(String rank) {
                    this.rank = rank;
                }

                public String getDisplayRank() {
                    return DisplayRank;
                }

                public void setDisplayRank(String DisplayRank) {
                    this.DisplayRank = DisplayRank;
                }

                public LevelInfoBeanXX getLevel_info() {
                    return level_info;
                }

                public void setLevel_info(LevelInfoBeanXX level_info) {
                    this.level_info = level_info;
                }

                public PendantBeanXX getPendant() {
                    return pendant;
                }

                public void setPendant(PendantBeanXX pendant) {
                    this.pendant = pendant;
                }

                public NameplateBeanXX getNameplate() {
                    return nameplate;
                }

                public void setNameplate(NameplateBeanXX nameplate) {
                    this.nameplate = nameplate;
                }

                public OfficialVerifyBeanXX getOfficial_verify() {
                    return official_verify;
                }

                public void setOfficial_verify(OfficialVerifyBeanXX official_verify) {
                    this.official_verify = official_verify;
                }

                public VipBeanXX getVip() {
                    return vip;
                }

                public void setVip(VipBeanXX vip) {
                    this.vip = vip;
                }

                public Object getFans_detail() {
                    return fans_detail;
                }

                public void setFans_detail(Object fans_detail) {
                    this.fans_detail = fans_detail;
                }

                public int getFollowing() {
                    return following;
                }

                public void setFollowing(int following) {
                    this.following = following;
                }

                public static class LevelInfoBeanXX {
                    private int current_level;
                    private int current_min;
                    private int current_exp;
                    private int next_exp;

                    public int getCurrent_level() {
                        return current_level;
                    }

                    public void setCurrent_level(int current_level) {
                        this.current_level = current_level;
                    }

                    public int getCurrent_min() {
                        return current_min;
                    }

                    public void setCurrent_min(int current_min) {
                        this.current_min = current_min;
                    }

                    public int getCurrent_exp() {
                        return current_exp;
                    }

                    public void setCurrent_exp(int current_exp) {
                        this.current_exp = current_exp;
                    }

                    public int getNext_exp() {
                        return next_exp;
                    }

                    public void setNext_exp(int next_exp) {
                        this.next_exp = next_exp;
                    }
                }

                public static class PendantBeanXX {
                    private int pid;
                    private String name;
                    private String image;
                    private int expire;

                    public int getPid() {
                        return pid;
                    }

                    public void setPid(int pid) {
                        this.pid = pid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public int getExpire() {
                        return expire;
                    }

                    public void setExpire(int expire) {
                        this.expire = expire;
                    }
                }

                public static class NameplateBeanXX {
                    private int nid;
                    private String name;
                    private String image;
                    private String image_small;
                    private String level;
                    private String condition;

                    public int getNid() {
                        return nid;
                    }

                    public void setNid(int nid) {
                        this.nid = nid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getImage_small() {
                        return image_small;
                    }

                    public void setImage_small(String image_small) {
                        this.image_small = image_small;
                    }

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
                    }

                    public String getCondition() {
                        return condition;
                    }

                    public void setCondition(String condition) {
                        this.condition = condition;
                    }
                }

                public static class OfficialVerifyBeanXX {
                    private int type;
                    private String desc;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class VipBeanXX {
                    private int vipType;
                    private long vipDueDate;
                    private String dueRemark;
                    private int accessStatus;
                    private int vipStatus;
                    private String vipStatusWarn;

                    public int getVipType() {
                        return vipType;
                    }

                    public void setVipType(int vipType) {
                        this.vipType = vipType;
                    }

                    public long getVipDueDate() {
                        return vipDueDate;
                    }

                    public void setVipDueDate(long vipDueDate) {
                        this.vipDueDate = vipDueDate;
                    }

                    public String getDueRemark() {
                        return dueRemark;
                    }

                    public void setDueRemark(String dueRemark) {
                        this.dueRemark = dueRemark;
                    }

                    public int getAccessStatus() {
                        return accessStatus;
                    }

                    public void setAccessStatus(int accessStatus) {
                        this.accessStatus = accessStatus;
                    }

                    public int getVipStatus() {
                        return vipStatus;
                    }

                    public void setVipStatus(int vipStatus) {
                        this.vipStatus = vipStatus;
                    }

                    public String getVipStatusWarn() {
                        return vipStatusWarn;
                    }

                    public void setVipStatusWarn(String vipStatusWarn) {
                        this.vipStatusWarn = vipStatusWarn;
                    }
                }
            }

            public static class ContentBeanXX {
                private String message;
                private int plat;
                private String device;
                private List<?> members;

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public int getPlat() {
                    return plat;
                }

                public void setPlat(int plat) {
                    this.plat = plat;
                }

                public String getDevice() {
                    return device;
                }

                public void setDevice(String device) {
                    this.device = device;
                }

                public List<?> getMembers() {
                    return members;
                }

                public void setMembers(List<?> members) {
                    this.members = members;
                }
            }

            public static class FolderBeanXX {
                private boolean has_folded;
                private boolean is_folded;
                private String rule;

                public boolean isHas_folded() {
                    return has_folded;
                }

                public void setHas_folded(boolean has_folded) {
                    this.has_folded = has_folded;
                }

                public boolean isIs_folded() {
                    return is_folded;
                }

                public void setIs_folded(boolean is_folded) {
                    this.is_folded = is_folded;
                }

                public String getRule() {
                    return rule;
                }

                public void setRule(String rule) {
                    this.rule = rule;
                }
            }

            public static class UpActionBeanXX {
                private boolean like;
                private boolean reply;

                public boolean isLike() {
                    return like;
                }

                public void setLike(boolean like) {
                    this.like = like;
                }

                public boolean isReply() {
                    return reply;
                }

                public void setReply(boolean reply) {
                    this.reply = reply;
                }
            }

            public static class RepliesBeanX {
                private int rpid;
                private int oid;
                private int type;
                private int mid;
                private int root;
                private int parent;
                private int dialog;
                private int count;
                private int rcount;
                private int floor;
                private int state;
                private int fansgrade;
                private int attr;
                private int ctime;
                private String rpid_str;
                private String root_str;
                private String parent_str;
                private String dialog_str;
                private int like;
                private int action;
                private MemberBeanXXX member;
                private ContentBeanXXX content;
                private int assist;
                private FolderBeanXXX folder;
                private UpActionBeanXXX up_action;
                private List<?> replies;

                public int getRpid() {
                    return rpid;
                }

                public void setRpid(int rpid) {
                    this.rpid = rpid;
                }

                public int getOid() {
                    return oid;
                }

                public void setOid(int oid) {
                    this.oid = oid;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getMid() {
                    return mid;
                }

                public void setMid(int mid) {
                    this.mid = mid;
                }

                public int getRoot() {
                    return root;
                }

                public void setRoot(int root) {
                    this.root = root;
                }

                public int getParent() {
                    return parent;
                }

                public void setParent(int parent) {
                    this.parent = parent;
                }

                public int getDialog() {
                    return dialog;
                }

                public void setDialog(int dialog) {
                    this.dialog = dialog;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public int getRcount() {
                    return rcount;
                }

                public void setRcount(int rcount) {
                    this.rcount = rcount;
                }

                public int getFloor() {
                    return floor;
                }

                public void setFloor(int floor) {
                    this.floor = floor;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public int getFansgrade() {
                    return fansgrade;
                }

                public void setFansgrade(int fansgrade) {
                    this.fansgrade = fansgrade;
                }

                public int getAttr() {
                    return attr;
                }

                public void setAttr(int attr) {
                    this.attr = attr;
                }

                public int getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
                    this.ctime = ctime;
                }

                public String getRpid_str() {
                    return rpid_str;
                }

                public void setRpid_str(String rpid_str) {
                    this.rpid_str = rpid_str;
                }

                public String getRoot_str() {
                    return root_str;
                }

                public void setRoot_str(String root_str) {
                    this.root_str = root_str;
                }

                public String getParent_str() {
                    return parent_str;
                }

                public void setParent_str(String parent_str) {
                    this.parent_str = parent_str;
                }

                public String getDialog_str() {
                    return dialog_str;
                }

                public void setDialog_str(String dialog_str) {
                    this.dialog_str = dialog_str;
                }

                public int getLike() {
                    return like;
                }

                public void setLike(int like) {
                    this.like = like;
                }

                public int getAction() {
                    return action;
                }

                public void setAction(int action) {
                    this.action = action;
                }

                public MemberBeanXXX getMember() {
                    return member;
                }

                public void setMember(MemberBeanXXX member) {
                    this.member = member;
                }

                public ContentBeanXXX getContent() {
                    return content;
                }

                public void setContent(ContentBeanXXX content) {
                    this.content = content;
                }

                public int getAssist() {
                    return assist;
                }

                public void setAssist(int assist) {
                    this.assist = assist;
                }

                public FolderBeanXXX getFolder() {
                    return folder;
                }

                public void setFolder(FolderBeanXXX folder) {
                    this.folder = folder;
                }

                public UpActionBeanXXX getUp_action() {
                    return up_action;
                }

                public void setUp_action(UpActionBeanXXX up_action) {
                    this.up_action = up_action;
                }

                public List<?> getReplies() {
                    return replies;
                }

                public void setReplies(List<?> replies) {
                    this.replies = replies;
                }

                public static class MemberBeanXXX {
                    private String mid;
                    private String uname;
                    private String sex;
                    private String sign;
                    private String avatar;
                    private String rank;
                    private String DisplayRank;
                    private LevelInfoBeanXXX level_info;
                    private PendantBeanXXX pendant;
                    private NameplateBeanXXX nameplate;
                    private OfficialVerifyBeanXXX official_verify;
                    private VipBeanXXX vip;
                    private Object fans_detail;
                    private int following;

                    public String getMid() {
                        return mid;
                    }

                    public void setMid(String mid) {
                        this.mid = mid;
                    }

                    public String getUname() {
                        return uname;
                    }

                    public void setUname(String uname) {
                        this.uname = uname;
                    }

                    public String getSex() {
                        return sex;
                    }

                    public void setSex(String sex) {
                        this.sex = sex;
                    }

                    public String getSign() {
                        return sign;
                    }

                    public void setSign(String sign) {
                        this.sign = sign;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getRank() {
                        return rank;
                    }

                    public void setRank(String rank) {
                        this.rank = rank;
                    }

                    public String getDisplayRank() {
                        return DisplayRank;
                    }

                    public void setDisplayRank(String DisplayRank) {
                        this.DisplayRank = DisplayRank;
                    }

                    public LevelInfoBeanXXX getLevel_info() {
                        return level_info;
                    }

                    public void setLevel_info(LevelInfoBeanXXX level_info) {
                        this.level_info = level_info;
                    }

                    public PendantBeanXXX getPendant() {
                        return pendant;
                    }

                    public void setPendant(PendantBeanXXX pendant) {
                        this.pendant = pendant;
                    }

                    public NameplateBeanXXX getNameplate() {
                        return nameplate;
                    }

                    public void setNameplate(NameplateBeanXXX nameplate) {
                        this.nameplate = nameplate;
                    }

                    public OfficialVerifyBeanXXX getOfficial_verify() {
                        return official_verify;
                    }

                    public void setOfficial_verify(OfficialVerifyBeanXXX official_verify) {
                        this.official_verify = official_verify;
                    }

                    public VipBeanXXX getVip() {
                        return vip;
                    }

                    public void setVip(VipBeanXXX vip) {
                        this.vip = vip;
                    }

                    public Object getFans_detail() {
                        return fans_detail;
                    }

                    public void setFans_detail(Object fans_detail) {
                        this.fans_detail = fans_detail;
                    }

                    public int getFollowing() {
                        return following;
                    }

                    public void setFollowing(int following) {
                        this.following = following;
                    }

                    public static class LevelInfoBeanXXX {
                        private int current_level;
                        private int current_min;
                        private int current_exp;
                        private int next_exp;

                        public int getCurrent_level() {
                            return current_level;
                        }

                        public void setCurrent_level(int current_level) {
                            this.current_level = current_level;
                        }

                        public int getCurrent_min() {
                            return current_min;
                        }

                        public void setCurrent_min(int current_min) {
                            this.current_min = current_min;
                        }

                        public int getCurrent_exp() {
                            return current_exp;
                        }

                        public void setCurrent_exp(int current_exp) {
                            this.current_exp = current_exp;
                        }

                        public int getNext_exp() {
                            return next_exp;
                        }

                        public void setNext_exp(int next_exp) {
                            this.next_exp = next_exp;
                        }
                    }

                    public static class PendantBeanXXX {
                        private int pid;
                        private String name;
                        private String image;
                        private int expire;

                        public int getPid() {
                            return pid;
                        }

                        public void setPid(int pid) {
                            this.pid = pid;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getImage() {
                            return image;
                        }

                        public void setImage(String image) {
                            this.image = image;
                        }

                        public int getExpire() {
                            return expire;
                        }

                        public void setExpire(int expire) {
                            this.expire = expire;
                        }
                    }

                    public static class NameplateBeanXXX {
                        private int nid;
                        private String name;
                        private String image;
                        private String image_small;
                        private String level;
                        private String condition;

                        public int getNid() {
                            return nid;
                        }

                        public void setNid(int nid) {
                            this.nid = nid;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getImage() {
                            return image;
                        }

                        public void setImage(String image) {
                            this.image = image;
                        }

                        public String getImage_small() {
                            return image_small;
                        }

                        public void setImage_small(String image_small) {
                            this.image_small = image_small;
                        }

                        public String getLevel() {
                            return level;
                        }

                        public void setLevel(String level) {
                            this.level = level;
                        }

                        public String getCondition() {
                            return condition;
                        }

                        public void setCondition(String condition) {
                            this.condition = condition;
                        }
                    }

                    public static class OfficialVerifyBeanXXX {
                        private int type;
                        private String desc;

                        public int getType() {
                            return type;
                        }

                        public void setType(int type) {
                            this.type = type;
                        }

                        public String getDesc() {
                            return desc;
                        }

                        public void setDesc(String desc) {
                            this.desc = desc;
                        }
                    }

                    public static class VipBeanXXX {
                        private int vipType;
                        private long vipDueDate;
                        private String dueRemark;
                        private int accessStatus;
                        private int vipStatus;
                        private String vipStatusWarn;

                        public int getVipType() {
                            return vipType;
                        }

                        public void setVipType(int vipType) {
                            this.vipType = vipType;
                        }

                        public long getVipDueDate() {
                            return vipDueDate;
                        }

                        public void setVipDueDate(long vipDueDate) {
                            this.vipDueDate = vipDueDate;
                        }

                        public String getDueRemark() {
                            return dueRemark;
                        }

                        public void setDueRemark(String dueRemark) {
                            this.dueRemark = dueRemark;
                        }

                        public int getAccessStatus() {
                            return accessStatus;
                        }

                        public void setAccessStatus(int accessStatus) {
                            this.accessStatus = accessStatus;
                        }

                        public int getVipStatus() {
                            return vipStatus;
                        }

                        public void setVipStatus(int vipStatus) {
                            this.vipStatus = vipStatus;
                        }

                        public String getVipStatusWarn() {
                            return vipStatusWarn;
                        }

                        public void setVipStatusWarn(String vipStatusWarn) {
                            this.vipStatusWarn = vipStatusWarn;
                        }
                    }
                }

                public static class ContentBeanXXX {
                    private String message;
                    private int plat;
                    private String device;
                    private List<?> members;

                    public String getMessage() {
                        return message;
                    }

                    public void setMessage(String message) {
                        this.message = message;
                    }

                    public int getPlat() {
                        return plat;
                    }

                    public void setPlat(int plat) {
                        this.plat = plat;
                    }

                    public String getDevice() {
                        return device;
                    }

                    public void setDevice(String device) {
                        this.device = device;
                    }

                    public List<?> getMembers() {
                        return members;
                    }

                    public void setMembers(List<?> members) {
                        this.members = members;
                    }
                }

                public static class FolderBeanXXX {
                    private boolean has_folded;
                    private boolean is_folded;
                    private String rule;

                    public boolean isHas_folded() {
                        return has_folded;
                    }

                    public void setHas_folded(boolean has_folded) {
                        this.has_folded = has_folded;
                    }

                    public boolean isIs_folded() {
                        return is_folded;
                    }

                    public void setIs_folded(boolean is_folded) {
                        this.is_folded = is_folded;
                    }

                    public String getRule() {
                        return rule;
                    }

                    public void setRule(String rule) {
                        this.rule = rule;
                    }
                }

                public static class UpActionBeanXXX {
                    private boolean like;
                    private boolean reply;

                    public boolean isLike() {
                        return like;
                    }

                    public void setLike(boolean like) {
                        this.like = like;
                    }

                    public boolean isReply() {
                        return reply;
                    }

                    public void setReply(boolean reply) {
                        this.reply = reply;
                    }
                }
            }
        }

        public static class RepliesBeanXX {
            private int rpid;
            private int oid;
            private int type;
            private int mid;
            private int root;
            private int parent;
            private int dialog;
            private int count;
            private int rcount;
            private int floor;
            private int state;
            private int fansgrade;
            private int attr;
            private int ctime;
            private String rpid_str;
            private String root_str;
            private String parent_str;
            private String dialog_str;
            private int like;
            private int action;
            private MemberBeanXXXX member;
            private ContentBeanXXXX content;
            private List<ReplyCursorBean.DataBean.HotsBean.RepliesBeanX> replies;
            private int assist;
            private FolderBeanXXXX folder;
            private UpActionBeanXXXX up_action;

            public int getRpid() {
                return rpid;
            }

            public void setRpid(int rpid) {
                this.rpid = rpid;
            }

            public int getOid() {
                return oid;
            }

            public void setOid(int oid) {
                this.oid = oid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public int getRoot() {
                return root;
            }

            public void setRoot(int root) {
                this.root = root;
            }

            public int getParent() {
                return parent;
            }

            public void setParent(int parent) {
                this.parent = parent;
            }

            public int getDialog() {
                return dialog;
            }

            public void setDialog(int dialog) {
                this.dialog = dialog;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getRcount() {
                return rcount;
            }

            public void setRcount(int rcount) {
                this.rcount = rcount;
            }

            public int getFloor() {
                return floor;
            }

            public void setFloor(int floor) {
                this.floor = floor;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getFansgrade() {
                return fansgrade;
            }

            public void setFansgrade(int fansgrade) {
                this.fansgrade = fansgrade;
            }

            public int getAttr() {
                return attr;
            }

            public void setAttr(int attr) {
                this.attr = attr;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public String getRpid_str() {
                return rpid_str;
            }

            public void setRpid_str(String rpid_str) {
                this.rpid_str = rpid_str;
            }

            public String getRoot_str() {
                return root_str;
            }

            public void setRoot_str(String root_str) {
                this.root_str = root_str;
            }

            public String getParent_str() {
                return parent_str;
            }

            public void setParent_str(String parent_str) {
                this.parent_str = parent_str;
            }

            public String getDialog_str() {
                return dialog_str;
            }

            public void setDialog_str(String dialog_str) {
                this.dialog_str = dialog_str;
            }

            public int getLike() {
                return like;
            }

            public void setLike(int like) {
                this.like = like;
            }

            public int getAction() {
                return action;
            }

            public void setAction(int action) {
                this.action = action;
            }

            public MemberBeanXXXX getMember() {
                return member;
            }

            public void setMember(MemberBeanXXXX member) {
                this.member = member;
            }

            public ContentBeanXXXX getContent() {
                return content;
            }

            public void setContent(ContentBeanXXXX content) {
                this.content = content;
            }

            public List<HotsBean.RepliesBeanX> getReplies() {
                return replies;
            }

            public void setReplies(List<HotsBean.RepliesBeanX> replies) {
                this.replies = replies;
            }

            public int getAssist() {
                return assist;
            }

            public void setAssist(int assist) {
                this.assist = assist;
            }

            public FolderBeanXXXX getFolder() {
                return folder;
            }

            public void setFolder(FolderBeanXXXX folder) {
                this.folder = folder;
            }

            public UpActionBeanXXXX getUp_action() {
                return up_action;
            }

            public void setUp_action(UpActionBeanXXXX up_action) {
                this.up_action = up_action;
            }

            public static class MemberBeanXXXX {
                private String mid;
                private String uname;
                private String sex;
                private String sign;
                private String avatar;
                private String rank;
                private String DisplayRank;
                private LevelInfoBeanXXXX level_info;
                private PendantBeanXXXX pendant;
                private NameplateBeanXXXX nameplate;
                private OfficialVerifyBeanXXXX official_verify;
                private VipBeanXXXX vip;
                private Object fans_detail;
                private int following;

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getRank() {
                    return rank;
                }

                public void setRank(String rank) {
                    this.rank = rank;
                }

                public String getDisplayRank() {
                    return DisplayRank;
                }

                public void setDisplayRank(String DisplayRank) {
                    this.DisplayRank = DisplayRank;
                }

                public LevelInfoBeanXXXX getLevel_info() {
                    return level_info;
                }

                public void setLevel_info(LevelInfoBeanXXXX level_info) {
                    this.level_info = level_info;
                }

                public PendantBeanXXXX getPendant() {
                    return pendant;
                }

                public void setPendant(PendantBeanXXXX pendant) {
                    this.pendant = pendant;
                }

                public NameplateBeanXXXX getNameplate() {
                    return nameplate;
                }

                public void setNameplate(NameplateBeanXXXX nameplate) {
                    this.nameplate = nameplate;
                }

                public OfficialVerifyBeanXXXX getOfficial_verify() {
                    return official_verify;
                }

                public void setOfficial_verify(OfficialVerifyBeanXXXX official_verify) {
                    this.official_verify = official_verify;
                }

                public VipBeanXXXX getVip() {
                    return vip;
                }

                public void setVip(VipBeanXXXX vip) {
                    this.vip = vip;
                }

                public Object getFans_detail() {
                    return fans_detail;
                }

                public void setFans_detail(Object fans_detail) {
                    this.fans_detail = fans_detail;
                }

                public int getFollowing() {
                    return following;
                }

                public void setFollowing(int following) {
                    this.following = following;
                }

                public static class LevelInfoBeanXXXX {
                    private int current_level;
                    private int current_min;
                    private int current_exp;
                    private int next_exp;

                    public int getCurrent_level() {
                        return current_level;
                    }

                    public void setCurrent_level(int current_level) {
                        this.current_level = current_level;
                    }

                    public int getCurrent_min() {
                        return current_min;
                    }

                    public void setCurrent_min(int current_min) {
                        this.current_min = current_min;
                    }

                    public int getCurrent_exp() {
                        return current_exp;
                    }

                    public void setCurrent_exp(int current_exp) {
                        this.current_exp = current_exp;
                    }

                    public int getNext_exp() {
                        return next_exp;
                    }

                    public void setNext_exp(int next_exp) {
                        this.next_exp = next_exp;
                    }
                }

                public static class PendantBeanXXXX {
                    private int pid;
                    private String name;
                    private String image;
                    private int expire;

                    public int getPid() {
                        return pid;
                    }

                    public void setPid(int pid) {
                        this.pid = pid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public int getExpire() {
                        return expire;
                    }

                    public void setExpire(int expire) {
                        this.expire = expire;
                    }
                }

                public static class NameplateBeanXXXX {
                    private int nid;
                    private String name;
                    private String image;
                    private String image_small;
                    private String level;
                    private String condition;

                    public int getNid() {
                        return nid;
                    }

                    public void setNid(int nid) {
                        this.nid = nid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getImage_small() {
                        return image_small;
                    }

                    public void setImage_small(String image_small) {
                        this.image_small = image_small;
                    }

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
                    }

                    public String getCondition() {
                        return condition;
                    }

                    public void setCondition(String condition) {
                        this.condition = condition;
                    }
                }

                public static class OfficialVerifyBeanXXXX {
                    private int type;
                    private String desc;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class VipBeanXXXX {
                    private int vipType;
                    private long vipDueDate;
                    private String dueRemark;
                    private int accessStatus;
                    private int vipStatus;
                    private String vipStatusWarn;

                    public int getVipType() {
                        return vipType;
                    }

                    public void setVipType(int vipType) {
                        this.vipType = vipType;
                    }

                    public long getVipDueDate() {
                        return vipDueDate;
                    }

                    public void setVipDueDate(long vipDueDate) {
                        this.vipDueDate = vipDueDate;
                    }

                    public String getDueRemark() {
                        return dueRemark;
                    }

                    public void setDueRemark(String dueRemark) {
                        this.dueRemark = dueRemark;
                    }

                    public int getAccessStatus() {
                        return accessStatus;
                    }

                    public void setAccessStatus(int accessStatus) {
                        this.accessStatus = accessStatus;
                    }

                    public int getVipStatus() {
                        return vipStatus;
                    }

                    public void setVipStatus(int vipStatus) {
                        this.vipStatus = vipStatus;
                    }

                    public String getVipStatusWarn() {
                        return vipStatusWarn;
                    }

                    public void setVipStatusWarn(String vipStatusWarn) {
                        this.vipStatusWarn = vipStatusWarn;
                    }
                }
            }

            public static class ContentBeanXXXX {
                private String message;
                private int plat;
                private String device;
                private List<?> members;

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public int getPlat() {
                    return plat;
                }

                public void setPlat(int plat) {
                    this.plat = plat;
                }

                public String getDevice() {
                    return device;
                }

                public void setDevice(String device) {
                    this.device = device;
                }

                public List<?> getMembers() {
                    return members;
                }

                public void setMembers(List<?> members) {
                    this.members = members;
                }
            }

            public static class FolderBeanXXXX {
                private boolean has_folded;
                private boolean is_folded;
                private String rule;

                public boolean isHas_folded() {
                    return has_folded;
                }

                public void setHas_folded(boolean has_folded) {
                    this.has_folded = has_folded;
                }

                public boolean isIs_folded() {
                    return is_folded;
                }

                public void setIs_folded(boolean is_folded) {
                    this.is_folded = is_folded;
                }

                public String getRule() {
                    return rule;
                }

                public void setRule(String rule) {
                    this.rule = rule;
                }
            }

            public static class UpActionBeanXXXX {
                private boolean like;
                private boolean reply;

                public boolean isLike() {
                    return like;
                }

                public void setLike(boolean like) {
                    this.like = like;
                }

                public boolean isReply() {
                    return reply;
                }

                public void setReply(boolean reply) {
                    this.reply = reply;
                }
            }
        }
    }
}
