package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import java.util.List;

public class BiliCommentBean {


    private long code;
    private String message;
    private long ttl;
    private DataBean data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private long assist;
        private long blacklist;
        private ConfigBean config;
        private CursorBean cursor;
        private NoticeBean notice;
        private TopBean top;
        private UpperBeanX upper;
        private long vote;
        private List<HotsBean> hots;
        private List<RepliesBean> replies;

        public long getAssist() {
            return assist;
        }

        public void setAssist(long assist) {
            this.assist = assist;
        }

        public long getBlacklist() {
            return blacklist;
        }

        public void setBlacklist(long blacklist) {
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

        public long getVote() {
            return vote;
        }

        public void setVote(long vote) {
            this.vote = vote;
        }

        public List<HotsBean> getHots() {
            return hots;
        }

        public void setHots(List<HotsBean> hots) {
            this.hots = hots;
        }

        public List<RepliesBean> getReplies() {
            return replies;
        }

        public void setReplies(List<RepliesBean> replies) {
            this.replies = replies;
        }

        public static class ConfigBean {
            private long showadmin;
            private long showentry;
            private long showfloor;
            private long showtopic;

            public long getShowadmin() {
                return showadmin;
            }

            public void setShowadmin(long showadmin) {
                this.showadmin = showadmin;
            }

            public long getShowentry() {
                return showentry;
            }

            public void setShowentry(long showentry) {
                this.showentry = showentry;
            }

            public long getShowfloor() {
                return showfloor;
            }

            public void setShowfloor(long showfloor) {
                this.showfloor = showfloor;
            }

            public long getShowtopic() {
                return showtopic;
            }

            public void setShowtopic(long showtopic) {
                this.showtopic = showtopic;
            }
        }

        public static class CursorBean {
            private long all_count;
            private long max_id;
            private long min_id;
            private long size;

            public long getAll_count() {
                return all_count;
            }

            public void setAll_count(long all_count) {
                this.all_count = all_count;
            }

            public long getMax_id() {
                return max_id;
            }

            public void setMax_id(long max_id) {
                this.max_id = max_id;
            }

            public long getMin_id() {
                return min_id;
            }

            public void setMin_id(long min_id) {
                this.min_id = min_id;
            }

            public long getSize() {
                return size;
            }

            public void setSize(long size) {
                this.size = size;
            }
        }

        public static class NoticeBean {
            private long id;
            private String title;
            private String content;
            private String link;

            public long getId() {
                return id;
            }

            public void setId(long id) {
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
                private long rpid;
                private long oid;
                private long type;
                private long mid;
                private long root;
                private long parent;
                private long dialog;
                private long count;
                private long rcount;
                private long floor;
                private long state;
                private long fansgrade;
                private long attr;
                private long ctime;
                private String rpid_str;
                private String root_str;
                private String parent_str;
                private String dialog_str;
                private long like;
                private long action;
                private MemberBean member;
                private ContentBean content;
                private long assist;
                private FolderBean folder;
                private UpActionBean up_action;
                private List<RepliesBean> replies;

                public long getRpid() {
                    return rpid;
                }

                public void setRpid(long rpid) {
                    this.rpid = rpid;
                }

                public long getOid() {
                    return oid;
                }

                public void setOid(long oid) {
                    this.oid = oid;
                }

                public long getType() {
                    return type;
                }

                public void setType(long type) {
                    this.type = type;
                }

                public long getMid() {
                    return mid;
                }

                public void setMid(long mid) {
                    this.mid = mid;
                }

                public long getRoot() {
                    return root;
                }

                public void setRoot(long root) {
                    this.root = root;
                }

                public long getParent() {
                    return parent;
                }

                public void setParent(long parent) {
                    this.parent = parent;
                }

                public long getDialog() {
                    return dialog;
                }

                public void setDialog(long dialog) {
                    this.dialog = dialog;
                }

                public long getCount() {
                    return count;
                }

                public void setCount(long count) {
                    this.count = count;
                }

                public long getRcount() {
                    return rcount;
                }

                public void setRcount(long rcount) {
                    this.rcount = rcount;
                }

                public long getFloor() {
                    return floor;
                }

                public void setFloor(long floor) {
                    this.floor = floor;
                }

                public long getState() {
                    return state;
                }

                public void setState(long state) {
                    this.state = state;
                }

                public long getFansgrade() {
                    return fansgrade;
                }

                public void setFansgrade(long fansgrade) {
                    this.fansgrade = fansgrade;
                }

                public long getAttr() {
                    return attr;
                }

                public void setAttr(long attr) {
                    this.attr = attr;
                }

                public long getCtime() {
                    return ctime;
                }

                public void setCtime(long ctime) {
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

                public long getLike() {
                    return like;
                }

                public void setLike(long like) {
                    this.like = like;
                }

                public long getAction() {
                    return action;
                }

                public void setAction(long action) {
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

                public long getAssist() {
                    return assist;
                }

                public void setAssist(long assist) {
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



                public static class ContentBean {
                    private String message;
                    private long plat;
                    private String device;
                    private List<?> members;

                    public String getMessage() {
                        return message;
                    }

                    public void setMessage(String message) {
                        this.message = message;
                    }

                    public long getPlat() {
                        return plat;
                    }

                    public void setPlat(long plat) {
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
            }
        }

        public static class UpperBeanX {
            private long mid;

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
            }
        }

        public static class HotsBean {
            private long rpid;
            private long oid;
            private long type;
            private long mid;
            private long root;
            private long parent;
            private long dialog;
            private long count;
            private long rcount;
            private long floor;
            private long state;
            private long fansgrade;
            private long attr;
            private long ctime;
            private String rpid_str;
            private String root_str;
            private String parent_str;
            private String dialog_str;
            private long like;
            private long action;
            private MemberBean member;
            private ContentBeanXX content;
            private long assist;
            private FolderBeanXX folder;
            private UpActionBeanXX up_action;
            private List<RepliesBean> replies;

            public long getRpid() {
                return rpid;
            }

            public void setRpid(long rpid) {
                this.rpid = rpid;
            }

            public long getOid() {
                return oid;
            }

            public void setOid(long oid) {
                this.oid = oid;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
            }

            public long getRoot() {
                return root;
            }

            public void setRoot(long root) {
                this.root = root;
            }

            public long getParent() {
                return parent;
            }

            public void setParent(long parent) {
                this.parent = parent;
            }

            public long getDialog() {
                return dialog;
            }

            public void setDialog(long dialog) {
                this.dialog = dialog;
            }

            public long getCount() {
                return count;
            }

            public void setCount(long count) {
                this.count = count;
            }

            public long getRcount() {
                return rcount;
            }

            public void setRcount(long rcount) {
                this.rcount = rcount;
            }

            public long getFloor() {
                return floor;
            }

            public void setFloor(long floor) {
                this.floor = floor;
            }

            public long getState() {
                return state;
            }

            public void setState(long state) {
                this.state = state;
            }

            public long getFansgrade() {
                return fansgrade;
            }

            public void setFansgrade(long fansgrade) {
                this.fansgrade = fansgrade;
            }

            public long getAttr() {
                return attr;
            }

            public void setAttr(long attr) {
                this.attr = attr;
            }

            public long getCtime() {
                return ctime;
            }

            public void setCtime(long ctime) {
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

            public long getLike() {
                return like;
            }

            public void setLike(long like) {
                this.like = like;
            }

            public long getAction() {
                return action;
            }

            public void setAction(long action) {
                this.action = action;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public ContentBeanXX getContent() {
                return content;
            }

            public void setContent(ContentBeanXX content) {
                this.content = content;
            }

            public long getAssist() {
                return assist;
            }

            public void setAssist(long assist) {
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

            public List<RepliesBean> getReplies() {
                return replies;
            }

            public void setReplies(List<RepliesBean> replies) {
                this.replies = replies;
            }


            public static class ContentBeanXX {
                private String message;
                private long plat;
                private String device;
                private List<?> members;

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public long getPlat() {
                    return plat;
                }

                public void setPlat(long plat) {
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
            private long following;

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

            public long getFollowing() {
                return following;
            }

            public void setFollowing(long following) {
                this.following = following;
            }

            public static class LevelInfoBean {
                private long current_level;
                private long current_min;
                private long current_exp;
                private long next_exp;

                public long getCurrent_level() {
                    return current_level;
                }

                public void setCurrent_level(long current_level) {
                    this.current_level = current_level;
                }

                public long getCurrent_min() {
                    return current_min;
                }

                public void setCurrent_min(long current_min) {
                    this.current_min = current_min;
                }

                public long getCurrent_exp() {
                    return current_exp;
                }

                public void setCurrent_exp(long current_exp) {
                    this.current_exp = current_exp;
                }

                public long getNext_exp() {
                    return next_exp;
                }

                public void setNext_exp(long next_exp) {
                    this.next_exp = next_exp;
                }
            }

            public static class PendantBean {
                private long pid;
                private String name;
                private String image;
                private long expire;

                public long getPid() {
                    return pid;
                }

                public void setPid(long pid) {
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

                public long getExpire() {
                    return expire;
                }

                public void setExpire(long expire) {
                    this.expire = expire;
                }
            }

            public static class NameplateBean {
                private long nid;
                private String name;
                private String image;
                private String image_small;
                private String level;
                private String condition;

                public long getNid() {
                    return nid;
                }

                public void setNid(long nid) {
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
                private long type;
                private String desc;

                public long getType() {
                    return type;
                }

                public void setType(long type) {
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
                private long vipType;
                private long vipDueDate;
                private String dueRemark;
                private long accessStatus;
                private long vipStatus;
                private String vipStatusWarn;

                public long getVipType() {
                    return vipType;
                }

                public void setVipType(long vipType) {
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

                public long getAccessStatus() {
                    return accessStatus;
                }

                public void setAccessStatus(long accessStatus) {
                    this.accessStatus = accessStatus;
                }

                public long getVipStatus() {
                    return vipStatus;
                }

                public void setVipStatus(long vipStatus) {
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

        public static class RepliesBean {
            private long rpid;
            private long oid;
            private long type;
            private long mid;
            private long root;
            private long parent;
            private long dialog;
            private long count;
            private long rcount;
            private long floor;
            private long state;
            private long fansgrade;
            private long attr;
            private long ctime;
            private String rpid_str;
            private String root_str;
            private String parent_str;
            private String dialog_str;
            private long like;
            private long action;
            private MemberBean member;
            private ContentBeanXXXX content;
            private List<RepliesBean> replies;
            private long assist;
            private FolderBeanXXXX folder;
            private UpActionBeanXXXX up_action;

            public long getRpid() {
                return rpid;
            }

            public void setRpid(long rpid) {
                this.rpid = rpid;
            }

            public long getOid() {
                return oid;
            }

            public void setOid(long oid) {
                this.oid = oid;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
            }

            public long getRoot() {
                return root;
            }

            public void setRoot(long root) {
                this.root = root;
            }

            public long getParent() {
                return parent;
            }

            public void setParent(long parent) {
                this.parent = parent;
            }

            public long getDialog() {
                return dialog;
            }

            public void setDialog(long dialog) {
                this.dialog = dialog;
            }

            public long getCount() {
                return count;
            }

            public void setCount(long count) {
                this.count = count;
            }

            public long getRcount() {
                return rcount;
            }

            public void setRcount(long rcount) {
                this.rcount = rcount;
            }

            public long getFloor() {
                return floor;
            }

            public void setFloor(long floor) {
                this.floor = floor;
            }

            public long getState() {
                return state;
            }

            public void setState(long state) {
                this.state = state;
            }

            public long getFansgrade() {
                return fansgrade;
            }

            public void setFansgrade(long fansgrade) {
                this.fansgrade = fansgrade;
            }

            public long getAttr() {
                return attr;
            }

            public void setAttr(long attr) {
                this.attr = attr;
            }

            public long getCtime() {
                return ctime;
            }

            public void setCtime(long ctime) {
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

            public long getLike() {
                return like;
            }

            public void setLike(long like) {
                this.like = like;
            }

            public long getAction() {
                return action;
            }

            public void setAction(long action) {
                this.action = action;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public ContentBeanXXXX getContent() {
                return content;
            }

            public void setContent(ContentBeanXXXX content) {
                this.content = content;
            }

            public List<RepliesBean> getReplies() {
                return replies;
            }

            public void setReplies(List<RepliesBean> replies) {
                this.replies = replies;
            }

            public long getAssist() {
                return assist;
            }

            public void setAssist(long assist) {
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

            public static class ContentBeanXXXX {
                private String message;
                private long plat;
                private String device;
                private List<?> members;

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public long getPlat() {
                    return plat;
                }

                public void setPlat(long plat) {
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
