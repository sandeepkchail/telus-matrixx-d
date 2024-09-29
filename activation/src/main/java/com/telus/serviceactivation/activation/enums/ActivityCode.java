package com.telus.serviceactivation.activation.enums;

public enum ActivityCode {
    NAC,CAN,RCL,SUS,RSP;
}
































/*


import org.apache.commons.lang3.StringUtils;

import lombok.Getter;


*/
/**
 * @author x332350
 *
 *//*


public enum ActivityCode {
    NUL("NUL", false, false),    // this is a fake transaction, represents null value
    GLS("GLS", true, false),    // GLS segment / Sub segment update
    S10("S10", false, false),    // Subscriber Info Update
    B51("B51", true, false),    // Update credit class
    B50("B50", true, false),    // Ban parameter changes
    RSP("RSP", false, false),    // Restore Suspend subscriber
    SUS("SUS", false, false),    // Suspend subscriber
    C23("C23", false, false),    // Contract Renewal
    B23("B23", true, false),    // Account Type / Subtype Changes
    B66("066", true, false),    // Billing cycle change confirm
    B65("065", true, false),    // Billing cycle change request
    MCN("MCN", false, true),        // Migration
    SFP("SFP", false, true),        // Subscriber Account Changes
    FSE("FSE", false, true),        // Profile changes including future dated txns
    SCH("SCH", false, true),        // Profile changes add/remove voice features
    CCN("CCN", false, true),        // Change Phone number
    RCL("RCL", false, true),        // Resume from cancellation
    CAN("CAN", false, true),        // Cancel subscriber
    NAC("NAC", false, true),        // Activation
    NCH("NCH", false, false),    // Network type change
    OSP("OSP", false, false),
    RECON_RCA("OSP", false, false),
    RECON_RCX("OSP", false, false),

    SPO("SPO", false, false),   //TIQ SIMPOWERON Txn
    //Below are Non-Provisioning;
    MGR("MGR", true, false, false),        //MGR,
    UCA("UCA", true, false, false),        //UCA, update Consent
    UPA("UPA", false, false, false),    //UPA, update Purchase Amount
    BL("BL", false, false, false),    //BL, block Subscriber
    UB("UB", false, false, false),    //UN, unblock Subscriber
    UBA("UBA", true, false, false),        //UBA, unblock Account
    SUF("SUF", false, false, false),    //SUF, update SelfBlockFlag
    UT("UT", false, false, false),    //UT, update Trigger
    UNT("UNT", false, false, false),    //UNT, update Notification Threshold (Both Subscriber and BAN level)
    ABS("ABS", true, false, false),        //ABS, get Account Block Status
    ANT("ANT", true, false, false),        //ANT, get Account Notification Threshold
    SGL("SGL", true, false, false),        //SGL, get Sharing Group List
    SBS("SBS", false, false, false),    //SBS, get Subscriber Block Status
    SNT("SNT", false, false, false),    //SNT, get Subscriber Notification Threshold
    STS("STS", false, false, false),    //STS, get Subscriber TriggerList
    SA("SA", false, false, false)        //SA,  get Subscriber Account
    ;

    @Getter
    private final String value;

    @Getter
    private final boolean banTxn;

    @Getter
    private final boolean featured;

    @Getter
    private final boolean provisioning;

    ActivityCode(String value, boolean isBanTxn) {
        this.value = value;
        this.banTxn = isBanTxn;
        featured = true;
        provisioning = true; //Default to true, will go to serviceProvisionController;
    }

    ActivityCode(String value, boolean isBanTxn, boolean featured) {
        this.value = value;
        this.banTxn = isBanTxn;
        this.featured = featured;
        provisioning = true; //Default to true, will go to serviceProvisionController;
    }

    // Add for Non-provisioning or custom;
    ActivityCode(String value, boolean isBanTxn, boolean featured, boolean provisioning) {
        this.value = value;
        this.banTxn = isBanTxn;
        this.featured = featured;
        this.provisioning = provisioning;
    }

    public boolean sameAs(String code) {
        if (code == null) return false;

        return this.value.equals(code);
    }

    public boolean equals(String code) {
        return sameAs(code);
    }

    public static ActivityCode parse(String code) {
        if (StringUtils.isBlank(code)) {
            return NUL;
        }

        for (ActivityCode c : ActivityCode.values()) {
            if (c.getValue().equals(code)) return c;
        }

        throw new IllegalArgumentException("Not supported activity code " + code);
    }
}
*/
