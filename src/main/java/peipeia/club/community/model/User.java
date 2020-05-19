package peipeia.club.community.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.id
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.name
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.account_id
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.gmt_create
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.gmt_modified
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.token
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.bio
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    private String bio;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.avatar_url
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.id
     *
     * @return the value of USER.id
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.id
     *
     * @param id the value for USER.id
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.name
     *
     * @return the value of USER.name
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.name
     *
     * @param name the value for USER.name
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.account_id
     *
     * @return the value of USER.account_id
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.account_id
     *
     * @param accountId the value for USER.account_id
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.gmt_create
     *
     * @return the value of USER.gmt_create
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.gmt_create
     *
     * @param gmtCreate the value for USER.gmt_create
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.gmt_modified
     *
     * @return the value of USER.gmt_modified
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.gmt_modified
     *
     * @param gmtModified the value for USER.gmt_modified
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.token
     *
     * @return the value of USER.token
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.token
     *
     * @param token the value for USER.token
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.bio
     *
     * @return the value of USER.bio
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public String getBio() {
        return bio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.bio
     *
     * @param bio the value for USER.bio
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.avatar_url
     *
     * @return the value of USER.avatar_url
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.avatar_url
     *
     * @param avatarUrl the value for USER.avatar_url
     *
     * @mbg.generated Tue May 12 15:02:24 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}