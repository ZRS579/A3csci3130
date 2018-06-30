package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */
public class Contact implements Serializable {

    /**
     * The Business number.
     */
    public  int businessNumber;
    /**
     * The Name.
     */
    public  String name;
    /**
     * The Primary business.
     */
    public  String primaryBusiness;
    /**
     * The Address.
     */
    public String address;
    /**
     * The Pro ter.
     */
    public String proTer;
    /**
     * The Uid.
     */
    public String uid;

    /**
     * Instantiates a new Contact.
     */
    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Instantiates a new Contact.
     *
     * @param uid             the uid
     * @param businessNumber  the business number
     * @param name            the name
     * @param primaryBusiness the primary business
     */
    public Contact(String uid, int businessNumber, String name, String primaryBusiness){
        this.uid = uid;
        this.name = name;
        this.businessNumber = businessNumber;
        this.primaryBusiness = primaryBusiness;
        proTer = "";
        address = "";
    }


    /**
     * Instantiates a new Contact.
     *
     * @param uid             the uid
     * @param businessNumber  the business number
     * @param name            the name
     * @param primaryBusiness the primary business
     * @param proTer          the pro ter
     * @param address         the address
     */
    public Contact(String uid, int businessNumber, String name, String primaryBusiness, String proTer, String address){
        this.uid = uid;
        this.name = name;
        this.businessNumber = businessNumber;
        this.primaryBusiness = primaryBusiness;
        this.proTer = proTer;
        this.address = address;
    }

    @Exclude
    private Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("businessNumber", businessNumber);
        result.put("address", address);
        result.put("proTer", proTer);

        return result;
    }
}
