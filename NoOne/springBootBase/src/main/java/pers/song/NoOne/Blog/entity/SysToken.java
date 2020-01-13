package pers.song.NoOne.Blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SysToken implements Serializable {
    private Integer id;

    private String account;

    private Integer accountId;

    private String token;

    private String statue;

    private Date dueDate;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue == null ? null : statue.trim();
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysToken sysToken = (SysToken) o;
        return Objects.equals(id, sysToken.id) &&
                Objects.equals(account, sysToken.account) &&
                Objects.equals(accountId, sysToken.accountId) &&
                Objects.equals(token, sysToken.token) &&
                Objects.equals(statue, sysToken.statue) &&
                Objects.equals(dueDate, sysToken.dueDate) &&
                Objects.equals(createDate, sysToken.createDate) &&
                Objects.equals(updateDate, sysToken.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, accountId, token, statue, dueDate, createDate, updateDate);
    }

    @Override
    public String toString() {
        return "SysToken{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", accountId=" + accountId +
                ", token='" + token + '\'' +
                ", statue='" + statue + '\'' +
                ", dueDate=" + dueDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}