package com.gbc.monday.UploadAPI.Model;

public class RawBody {

    String api_key;
    String avatar_media_url;
    String item_uuid;

    public RawBody(String api_key, String avatar_media_url, String item_uuid) {
        this.api_key = api_key;
        this.avatar_media_url = avatar_media_url;
        this.item_uuid = item_uuid;
    }
}
