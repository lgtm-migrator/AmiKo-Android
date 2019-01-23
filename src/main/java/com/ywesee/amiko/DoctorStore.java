package com.ywesee.amiko;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class DoctorStore {
    private String dir;

    public String title;
    public String name;
    public String surname;
    public String street;
    public String city;
    public String zip;
    public String phone;
    public String email;

    public DoctorStore(String baseDir) {
        dir = baseDir;
    }

    public void load() {
        String targetPath = new File(dir, "doctor.txt").getPath();
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            FileInputStream fileIn = new FileInputStream(targetPath);
            // Make sure there is something to read...
            if (fileIn.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(fileIn);
                map = (HashMap<String, String>)in.readObject();
                in.close();
            }
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.title = map.get("title");
        this.name = map.get("name");
        this.surname = map.get("surname");
        this.street = map.get("street");
        this.city = map.get("city");
        this.zip = map.get("zip");
        this.phone = map.get("phone");
        this.email = map.get("email");
    }

    public void save() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title", this.title);
        map.put("name", this.name);
        map.put("surname", this.surname);
        map.put("street", this.street);
        map.put("city", this.city);
        map.put("zip", this.zip);
        map.put("phone", this.phone);
        map.put("email", this.email);

        try {
            String targetPath = new File(dir, "doctor.txt").getPath();
            FileOutputStream fileOut = new FileOutputStream(targetPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getSignature() {
        String targetPath = new File(dir, "doctor.png").getPath();
        Bitmap bm = BitmapFactory.decodeFile(targetPath);
        return bm;
    }

    public void saveSignature(Bitmap bitmap) {
        try {
            String targetPath = new File(dir, "doctor.png").getPath();
            File file = new File(targetPath);
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}