package com.rony.e_commerceapp.Response;

import java.util.ArrayList;

public class SliderResponse {


    public ArrayList<Datum> data;

    public SliderResponse(ArrayList<Datum> data) {
        this.data = data;
    }

    public class Datum{
        public String title;
        public String url;
        public String image;
    }
}
