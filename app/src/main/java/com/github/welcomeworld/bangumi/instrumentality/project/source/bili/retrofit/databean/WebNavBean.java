package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class WebNavBean {

    /**
     * code
     */
    @SerializedName("code")
    private long code;
    /**
     * message
     */
    @SerializedName("message")
    private String message;
    /**
     * ttl
     */
    @SerializedName("ttl")
    private long ttl;
    /**
     * data
     */
    @SerializedName("data")
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        /**
         * isLogin
         */
        @SerializedName("isLogin")
        private boolean isLogin;
        /**
         * emailVerified
         */
        @SerializedName("email_verified")
        private long emailVerified;
        /**
         * face
         */
        @SerializedName("face")
        private String face;
        /**
         * faceNft
         */
        @SerializedName("face_nft")
        private long faceNft;
        /**
         * faceNftType
         */
        @SerializedName("face_nft_type")
        private long faceNftType;
        /**
         * levelInfo
         */
        @SerializedName("level_info")
        private LevelInfo levelInfo;
        /**
         * mid
         */
        @SerializedName("mid")
        private long mid;
        /**
         * mobileVerified
         */
        @SerializedName("mobile_verified")
        private long mobileVerified;
        /**
         * money
         */
        @SerializedName("money")
        private double money;
        /**
         * moral
         */
        @SerializedName("moral")
        private long moral;
        /**
         * official
         */
        @SerializedName("official")
        private Official official;
        /**
         * officialVerify
         */
        @SerializedName("officialVerify")
        private OfficialVerify officialVerify;
        /**
         * pendant
         */
        @SerializedName("pendant")
        private Pendant pendant;
        /**
         * scores
         */
        @SerializedName("scores")
        private long scores;
        /**
         * uname
         */
        @SerializedName("uname")
        private String uname;
        /**
         * vipDueDate
         */
        @SerializedName("vipDueDate")
        private long vipDueDate;
        /**
         * vipStatus
         */
        @SerializedName("vipStatus")
        private long vipStatus;
        /**
         * vipType
         */
        @SerializedName("vipType")
        private long vipType;
        /**
         * vipPayType
         */
        @SerializedName("vip_pay_type")
        private long vipPayType;
        /**
         * vipThemeType
         */
        @SerializedName("vip_theme_type")
        private long vipThemeType;
        /**
         * vipLabel
         */
        @SerializedName("vip_label")
        private VipLabel vipLabel;
        /**
         * vipAvatarSubscript
         */
        @SerializedName("vip_avatar_subscript")
        private long vipAvatarSubscript;
        /**
         * vipNicknameColor
         */
        @SerializedName("vip_nickname_color")
        private String vipNicknameColor;
        /**
         * vip
         */
        @SerializedName("vip")
        private Vip vip;
        /**
         * wallet
         */
        @SerializedName("wallet")
        private Wallet wallet;
        /**
         * hasShop
         */
        @SerializedName("has_shop")
        private boolean hasShop;
        /**
         * shopUrl
         */
        @SerializedName("shop_url")
        private String shopUrl;
        /**
         * allowanceCount
         */
        @SerializedName("allowance_count")
        private long allowanceCount;
        /**
         * answerStatus
         */
        @SerializedName("answer_status")
        private long answerStatus;
        /**
         * isSeniorMember
         */
        @SerializedName("is_senior_member")
        private long isSeniorMember;
        /**
         * wbiImg
         */
        @SerializedName("wbi_img")
        private WbiImg wbiImg;
        /**
         * isJury
         */
        @SerializedName("is_jury")
        private boolean isJury;

        public boolean isIsLogin() {
            return isLogin;
        }

        public void setIsLogin(boolean isLogin) {
            this.isLogin = isLogin;
        }

        public long getEmailVerified() {
            return emailVerified;
        }

        public void setEmailVerified(long emailVerified) {
            this.emailVerified = emailVerified;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public long getFaceNft() {
            return faceNft;
        }

        public void setFaceNft(long faceNft) {
            this.faceNft = faceNft;
        }

        public long getFaceNftType() {
            return faceNftType;
        }

        public void setFaceNftType(long faceNftType) {
            this.faceNftType = faceNftType;
        }

        public LevelInfo getLevelInfo() {
            return levelInfo;
        }

        public void setLevelInfo(LevelInfo levelInfo) {
            this.levelInfo = levelInfo;
        }

        public long getMid() {
            return mid;
        }

        public void setMid(long mid) {
            this.mid = mid;
        }

        public long getMobileVerified() {
            return mobileVerified;
        }

        public void setMobileVerified(long mobileVerified) {
            this.mobileVerified = mobileVerified;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public long getMoral() {
            return moral;
        }

        public void setMoral(long moral) {
            this.moral = moral;
        }

        public Official getOfficial() {
            return official;
        }

        public void setOfficial(Official official) {
            this.official = official;
        }

        public OfficialVerify getOfficialVerify() {
            return officialVerify;
        }

        public void setOfficialVerify(OfficialVerify officialVerify) {
            this.officialVerify = officialVerify;
        }

        public Pendant getPendant() {
            return pendant;
        }

        public void setPendant(Pendant pendant) {
            this.pendant = pendant;
        }

        public long getScores() {
            return scores;
        }

        public void setScores(long scores) {
            this.scores = scores;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public long getVipDueDate() {
            return vipDueDate;
        }

        public void setVipDueDate(long vipDueDate) {
            this.vipDueDate = vipDueDate;
        }

        public long getVipStatus() {
            return vipStatus;
        }

        public void setVipStatus(long vipStatus) {
            this.vipStatus = vipStatus;
        }

        public long getVipType() {
            return vipType;
        }

        public void setVipType(long vipType) {
            this.vipType = vipType;
        }

        public long getVipPayType() {
            return vipPayType;
        }

        public void setVipPayType(long vipPayType) {
            this.vipPayType = vipPayType;
        }

        public long getVipThemeType() {
            return vipThemeType;
        }

        public void setVipThemeType(long vipThemeType) {
            this.vipThemeType = vipThemeType;
        }

        public VipLabel getVipLabel() {
            return vipLabel;
        }

        public void setVipLabel(VipLabel vipLabel) {
            this.vipLabel = vipLabel;
        }

        public long getVipAvatarSubscript() {
            return vipAvatarSubscript;
        }

        public void setVipAvatarSubscript(long vipAvatarSubscript) {
            this.vipAvatarSubscript = vipAvatarSubscript;
        }

        public String getVipNicknameColor() {
            return vipNicknameColor;
        }

        public void setVipNicknameColor(String vipNicknameColor) {
            this.vipNicknameColor = vipNicknameColor;
        }

        public Vip getVip() {
            return vip;
        }

        public void setVip(Vip vip) {
            this.vip = vip;
        }

        public Wallet getWallet() {
            return wallet;
        }

        public void setWallet(Wallet wallet) {
            this.wallet = wallet;
        }

        public boolean isHasShop() {
            return hasShop;
        }

        public void setHasShop(boolean hasShop) {
            this.hasShop = hasShop;
        }

        public String getShopUrl() {
            return shopUrl;
        }

        public void setShopUrl(String shopUrl) {
            this.shopUrl = shopUrl;
        }

        public long getAllowanceCount() {
            return allowanceCount;
        }

        public void setAllowanceCount(long allowanceCount) {
            this.allowanceCount = allowanceCount;
        }

        public long getAnswerStatus() {
            return answerStatus;
        }

        public void setAnswerStatus(long answerStatus) {
            this.answerStatus = answerStatus;
        }

        public long getIsSeniorMember() {
            return isSeniorMember;
        }

        public void setIsSeniorMember(long isSeniorMember) {
            this.isSeniorMember = isSeniorMember;
        }

        public WbiImg getWbiImg() {
            return wbiImg;
        }

        public void setWbiImg(WbiImg wbiImg) {
            this.wbiImg = wbiImg;
        }

        public boolean isIsJury() {
            return isJury;
        }

        public void setIsJury(boolean isJury) {
            this.isJury = isJury;
        }

        public static class LevelInfo {
            /**
             * currentLevel
             */
            @SerializedName("current_level")
            private long currentLevel;
            /**
             * currentMin
             */
            @SerializedName("current_min")
            private long currentMin;
            /**
             * currentExp
             */
            @SerializedName("current_exp")
            private long currentExp;
            /**
             * nextExp
             */
            @SerializedName("next_exp")
            private String nextExp;

            public long getCurrentLevel() {
                return currentLevel;
            }

            public void setCurrentLevel(long currentLevel) {
                this.currentLevel = currentLevel;
            }

            public long getCurrentMin() {
                return currentMin;
            }

            public void setCurrentMin(long currentMin) {
                this.currentMin = currentMin;
            }

            public long getCurrentExp() {
                return currentExp;
            }

            public void setCurrentExp(long currentExp) {
                this.currentExp = currentExp;
            }

            public String getNextExp() {
                return nextExp;
            }

            public void setNextExp(String nextExp) {
                this.nextExp = nextExp;
            }
        }

        public static class Official {
            /**
             * role
             */
            @SerializedName("role")
            private long role;
            /**
             * title
             */
            @SerializedName("title")
            private String title;
            /**
             * desc
             */
            @SerializedName("desc")
            private String desc;
            /**
             * type
             */
            @SerializedName("type")
            private long type;

            public long getRole() {
                return role;
            }

            public void setRole(long role) {
                this.role = role;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }
        }

        public static class OfficialVerify {
            /**
             * type
             */
            @SerializedName("type")
            private long type;
            /**
             * desc
             */
            @SerializedName("desc")
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

        public static class Pendant {
            /**
             * pid
             */
            @SerializedName("pid")
            private long pid;
            /**
             * name
             */
            @SerializedName("name")
            private String name;
            /**
             * image
             */
            @SerializedName("image")
            private String image;
            /**
             * expire
             */
            @SerializedName("expire")
            private long expire;
            /**
             * imageEnhance
             */
            @SerializedName("image_enhance")
            private String imageEnhance;
            /**
             * imageEnhanceFrame
             */
            @SerializedName("image_enhance_frame")
            private String imageEnhanceFrame;

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

            public String getImageEnhance() {
                return imageEnhance;
            }

            public void setImageEnhance(String imageEnhance) {
                this.imageEnhance = imageEnhance;
            }

            public String getImageEnhanceFrame() {
                return imageEnhanceFrame;
            }

            public void setImageEnhanceFrame(String imageEnhanceFrame) {
                this.imageEnhanceFrame = imageEnhanceFrame;
            }
        }

        public static class VipLabel {
            /**
             * path
             */
            @SerializedName("path")
            private String path;
            /**
             * text
             */
            @SerializedName("text")
            private String text;
            /**
             * labelTheme
             */
            @SerializedName("label_theme")
            private String labelTheme;
            /**
             * textColor
             */
            @SerializedName("text_color")
            private String textColor;
            /**
             * bgStyle
             */
            @SerializedName("bg_style")
            private long bgStyle;
            /**
             * bgColor
             */
            @SerializedName("bg_color")
            private String bgColor;
            /**
             * borderColor
             */
            @SerializedName("border_color")
            private String borderColor;
            /**
             * useImgLabel
             */
            @SerializedName("use_img_label")
            private boolean useImgLabel;
            /**
             * imgLabelUriHans
             */
            @SerializedName("img_label_uri_hans")
            private String imgLabelUriHans;
            /**
             * imgLabelUriHant
             */
            @SerializedName("img_label_uri_hant")
            private String imgLabelUriHant;
            /**
             * imgLabelUriHansStatic
             */
            @SerializedName("img_label_uri_hans_static")
            private String imgLabelUriHansStatic;
            /**
             * imgLabelUriHantStatic
             */
            @SerializedName("img_label_uri_hant_static")
            private String imgLabelUriHantStatic;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getLabelTheme() {
                return labelTheme;
            }

            public void setLabelTheme(String labelTheme) {
                this.labelTheme = labelTheme;
            }

            public String getTextColor() {
                return textColor;
            }

            public void setTextColor(String textColor) {
                this.textColor = textColor;
            }

            public long getBgStyle() {
                return bgStyle;
            }

            public void setBgStyle(long bgStyle) {
                this.bgStyle = bgStyle;
            }

            public String getBgColor() {
                return bgColor;
            }

            public void setBgColor(String bgColor) {
                this.bgColor = bgColor;
            }

            public String getBorderColor() {
                return borderColor;
            }

            public void setBorderColor(String borderColor) {
                this.borderColor = borderColor;
            }

            public boolean isUseImgLabel() {
                return useImgLabel;
            }

            public void setUseImgLabel(boolean useImgLabel) {
                this.useImgLabel = useImgLabel;
            }

            public String getImgLabelUriHans() {
                return imgLabelUriHans;
            }

            public void setImgLabelUriHans(String imgLabelUriHans) {
                this.imgLabelUriHans = imgLabelUriHans;
            }

            public String getImgLabelUriHant() {
                return imgLabelUriHant;
            }

            public void setImgLabelUriHant(String imgLabelUriHant) {
                this.imgLabelUriHant = imgLabelUriHant;
            }

            public String getImgLabelUriHansStatic() {
                return imgLabelUriHansStatic;
            }

            public void setImgLabelUriHansStatic(String imgLabelUriHansStatic) {
                this.imgLabelUriHansStatic = imgLabelUriHansStatic;
            }

            public String getImgLabelUriHantStatic() {
                return imgLabelUriHantStatic;
            }

            public void setImgLabelUriHantStatic(String imgLabelUriHantStatic) {
                this.imgLabelUriHantStatic = imgLabelUriHantStatic;
            }
        }

        public static class Vip {
            /**
             * type
             */
            @SerializedName("type")
            private long type;
            /**
             * status
             */
            @SerializedName("status")
            private long status;
            /**
             * dueDate
             */
            @SerializedName("due_date")
            private long dueDate;
            /**
             * vipPayType
             */
            @SerializedName("vip_pay_type")
            private long vipPayType;
            /**
             * themeType
             */
            @SerializedName("theme_type")
            private long themeType;
            /**
             * label
             */
            @SerializedName("label")
            private Label label;
            /**
             * avatarSubscript
             */
            @SerializedName("avatar_subscript")
            private long avatarSubscript;
            /**
             * nicknameColor
             */
            @SerializedName("nickname_color")
            private String nicknameColor;
            /**
             * role
             */
            @SerializedName("role")
            private long role;
            /**
             * avatarSubscriptUrl
             */
            @SerializedName("avatar_subscript_url")
            private String avatarSubscriptUrl;
            /**
             * tvVipStatus
             */
            @SerializedName("tv_vip_status")
            private long tvVipStatus;
            /**
             * tvVipPayType
             */
            @SerializedName("tv_vip_pay_type")
            private long tvVipPayType;
            /**
             * tvDueDate
             */
            @SerializedName("tv_due_date")
            private long tvDueDate;

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public long getStatus() {
                return status;
            }

            public void setStatus(long status) {
                this.status = status;
            }

            public long getDueDate() {
                return dueDate;
            }

            public void setDueDate(long dueDate) {
                this.dueDate = dueDate;
            }

            public long getVipPayType() {
                return vipPayType;
            }

            public void setVipPayType(long vipPayType) {
                this.vipPayType = vipPayType;
            }

            public long getThemeType() {
                return themeType;
            }

            public void setThemeType(long themeType) {
                this.themeType = themeType;
            }

            public Label getLabel() {
                return label;
            }

            public void setLabel(Label label) {
                this.label = label;
            }

            public long getAvatarSubscript() {
                return avatarSubscript;
            }

            public void setAvatarSubscript(long avatarSubscript) {
                this.avatarSubscript = avatarSubscript;
            }

            public String getNicknameColor() {
                return nicknameColor;
            }

            public void setNicknameColor(String nicknameColor) {
                this.nicknameColor = nicknameColor;
            }

            public long getRole() {
                return role;
            }

            public void setRole(long role) {
                this.role = role;
            }

            public String getAvatarSubscriptUrl() {
                return avatarSubscriptUrl;
            }

            public void setAvatarSubscriptUrl(String avatarSubscriptUrl) {
                this.avatarSubscriptUrl = avatarSubscriptUrl;
            }

            public long getTvVipStatus() {
                return tvVipStatus;
            }

            public void setTvVipStatus(long tvVipStatus) {
                this.tvVipStatus = tvVipStatus;
            }

            public long getTvVipPayType() {
                return tvVipPayType;
            }

            public void setTvVipPayType(long tvVipPayType) {
                this.tvVipPayType = tvVipPayType;
            }

            public long getTvDueDate() {
                return tvDueDate;
            }

            public void setTvDueDate(long tvDueDate) {
                this.tvDueDate = tvDueDate;
            }

            public static class Label {
                /**
                 * path
                 */
                @SerializedName("path")
                private String path;
                /**
                 * text
                 */
                @SerializedName("text")
                private String text;
                /**
                 * labelTheme
                 */
                @SerializedName("label_theme")
                private String labelTheme;
                /**
                 * textColor
                 */
                @SerializedName("text_color")
                private String textColor;
                /**
                 * bgStyle
                 */
                @SerializedName("bg_style")
                private long bgStyle;
                /**
                 * bgColor
                 */
                @SerializedName("bg_color")
                private String bgColor;
                /**
                 * borderColor
                 */
                @SerializedName("border_color")
                private String borderColor;
                /**
                 * useImgLabel
                 */
                @SerializedName("use_img_label")
                private boolean useImgLabel;
                /**
                 * imgLabelUriHans
                 */
                @SerializedName("img_label_uri_hans")
                private String imgLabelUriHans;
                /**
                 * imgLabelUriHant
                 */
                @SerializedName("img_label_uri_hant")
                private String imgLabelUriHant;
                /**
                 * imgLabelUriHansStatic
                 */
                @SerializedName("img_label_uri_hans_static")
                private String imgLabelUriHansStatic;
                /**
                 * imgLabelUriHantStatic
                 */
                @SerializedName("img_label_uri_hant_static")
                private String imgLabelUriHantStatic;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getLabelTheme() {
                    return labelTheme;
                }

                public void setLabelTheme(String labelTheme) {
                    this.labelTheme = labelTheme;
                }

                public String getTextColor() {
                    return textColor;
                }

                public void setTextColor(String textColor) {
                    this.textColor = textColor;
                }

                public long getBgStyle() {
                    return bgStyle;
                }

                public void setBgStyle(long bgStyle) {
                    this.bgStyle = bgStyle;
                }

                public String getBgColor() {
                    return bgColor;
                }

                public void setBgColor(String bgColor) {
                    this.bgColor = bgColor;
                }

                public String getBorderColor() {
                    return borderColor;
                }

                public void setBorderColor(String borderColor) {
                    this.borderColor = borderColor;
                }

                public boolean isUseImgLabel() {
                    return useImgLabel;
                }

                public void setUseImgLabel(boolean useImgLabel) {
                    this.useImgLabel = useImgLabel;
                }

                public String getImgLabelUriHans() {
                    return imgLabelUriHans;
                }

                public void setImgLabelUriHans(String imgLabelUriHans) {
                    this.imgLabelUriHans = imgLabelUriHans;
                }

                public String getImgLabelUriHant() {
                    return imgLabelUriHant;
                }

                public void setImgLabelUriHant(String imgLabelUriHant) {
                    this.imgLabelUriHant = imgLabelUriHant;
                }

                public String getImgLabelUriHansStatic() {
                    return imgLabelUriHansStatic;
                }

                public void setImgLabelUriHansStatic(String imgLabelUriHansStatic) {
                    this.imgLabelUriHansStatic = imgLabelUriHansStatic;
                }

                public String getImgLabelUriHantStatic() {
                    return imgLabelUriHantStatic;
                }

                public void setImgLabelUriHantStatic(String imgLabelUriHantStatic) {
                    this.imgLabelUriHantStatic = imgLabelUriHantStatic;
                }
            }
        }

        public static class Wallet {
            /**
             * mid
             */
            @SerializedName("mid")
            private long mid;
            /**
             * bcoinBalance
             */
            @SerializedName("bcoin_balance")
            private long bcoinBalance;
            /**
             * couponBalance
             */
            @SerializedName("coupon_balance")
            private long couponBalance;
            /**
             * couponDueTime
             */
            @SerializedName("coupon_due_time")
            private long couponDueTime;

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
            }

            public long getBcoinBalance() {
                return bcoinBalance;
            }

            public void setBcoinBalance(long bcoinBalance) {
                this.bcoinBalance = bcoinBalance;
            }

            public long getCouponBalance() {
                return couponBalance;
            }

            public void setCouponBalance(long couponBalance) {
                this.couponBalance = couponBalance;
            }

            public long getCouponDueTime() {
                return couponDueTime;
            }

            public void setCouponDueTime(long couponDueTime) {
                this.couponDueTime = couponDueTime;
            }
        }

        public static class WbiImg {
            /**
             * imgUrl
             */
            @SerializedName("img_url")
            private String imgUrl;
            /**
             * subUrl
             */
            @SerializedName("sub_url")
            private String subUrl;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getSubUrl() {
                return subUrl;
            }

            public void setSubUrl(String subUrl) {
                this.subUrl = subUrl;
            }
        }
    }
}
