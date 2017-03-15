package com.example.bowen.myappframe.protocol.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dongbowen on 2016/6/10.
 */
public class Contacts extends Response implements Serializable {

    private List<Contact> returnData;

    public class Contact implements Serializable {
        String contactName;
        String contactMobile;
        String contactPwd;
        String relation;
        String id;
        String isContacted;  //0为未通知
        String isDeleted;    //1为失效

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getIsContacted() {
            return isContacted;
        }

        public void setIsContacted(String isContacted) {
            this.isContacted = isContacted;
        }

        public void setContactName(String name){
            contactName = name;
        }

        public String getContactName(){
            return contactName;
        }

        public void setMobile(String m){
            contactMobile = m;
        }

        public String getMobile(){
            return contactMobile;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getContactPwd() {
            return contactPwd;
        }

        public void setContactPwd(String contactPwd) {
            this.contactPwd = contactPwd;
        }

        public String getId(){
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public List<Contact> getReturnData() {
        return returnData;
    }

    public void setReturnData(List<Contact> returnData) {
        this.returnData = returnData;
    }

}
