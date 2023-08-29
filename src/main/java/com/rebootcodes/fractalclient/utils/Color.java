package com.rebootcodes.fractalclient.utils;

public class Color {
    public Float r;
    public Float g;
    public Float b;
    public Float a;

    public Color(int r, int g, int b) {
        this.r = (float) r;
        this.g = (float) g;
        this.b = (float) b;
        this.a = 255.0F;
    }

    public Color(int r, int g, int b, int a) {
        this.r = (float) r;
        this.g = (float) g;
        this.b = (float) b;
        this.a = (float) a;
    }

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = g;
        this.a = 255.0F;
    }

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
