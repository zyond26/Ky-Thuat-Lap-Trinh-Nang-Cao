package com.example.DemoProject;

public class Calculator {
    private double a, b, c;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    // Phương thức tính nghiệm (tuỳ chọn, nếu muốn dùng ở backend)
    public String solve() {
        if (a == 0) {
            if (b == 0) {
                return c == 0 ? "Phương trình có vô số nghiệm." : "Phương trình vô nghiệm.";
            } else {
                return String.format("Phương trình có một nghiệm: x = %.2f", -c / b);
            }
        }
        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return String.format("Phuong trinh co 2 nghiem : x₁ = %.2f, x₂ = %.2f", x1, x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return String.format("Phuong trinh co nghiem keo: x = %.2f", x);
        } else {
            return "Phuong trinh vo nghiep(delta < 0).";
        }
    }
}