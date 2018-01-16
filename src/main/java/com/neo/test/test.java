package com.neo.test;

import java.util.Scanner;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/1/15 13:52
 * @Modified By:
 */
public class test {


    private final double PI = 3.1415927;

    // 获取圆心角的一半
    double getHalfCircleAngle(float r, float h) {
        if (h > r || h <= 0) {
            return 0;
        }
        float cos_value = (r - h) / r;
        return Math.acos(cos_value);
    }

    // 获取弦长的一半
    double getHalfArc(float r, float h) {
        return Math.sqrt(r * r - (r - h) * (r - h));
    }

    // 获取圆的面积
    double getCircleArea(float r) {
        return PI * r * r;
    }

    // 获取弧的面积
    double getArcArea(float r, float h) {
        double half_arc = getHalfArc(r, h);
        double circle_angle = 2 * getHalfCircleAngle(r, h);
        double area = 0.5 * circle_angle * r * r - half_arc * Math.sqrt(r * r - half_arc * half_arc);
        return area;
    }

    public static void main(String[] args) {
        // 变量定义
        float r = 1;
        float start = 0, end = 1, middle;
        double flag = 0;
        double flag1 = 0;
        double flag2 = 0;
        double arc_area = 0;
        double ratio;
        test test=new test();
        // 输入比率
        System.out.println("please input the ratio:\n");
        Scanner scan = new Scanner(System.in);
        ratio = scan.nextDouble();
        // 将比率转化为大于1
        if (ratio < 1) {
            ratio = 1 / ratio;
        }
        // 获取圆的面积
        double area = test.getCircleArea(r);
        // 初始化迭代值
        middle = (start + end) / 2;
        double arc_area1 = test.getArcArea(r, start);
        double arc_area2 = test.getArcArea(r, end);
        if (arc_area1 == 0) {
            flag1 = 10000000;
        } else {
            flag1 = (area - arc_area1) / arc_area1;
        }
        flag2 = (area - arc_area2) / arc_area2;
        // 二分法取值
        while (flag1 >= ratio && flag2 <= ratio) {
            arc_area = test.getArcArea(r, middle);
            flag = (area - arc_area) / arc_area;
            System.out.println("flag1="+flag1);
            System.out.println("flag2="+flag2);
            System.out.println("flag="+flag);
            System.out.println("middle="+middle);
            if (flag > ratio) {
                if ((flag - ratio) < 0.0001) {
                    break;
                }
                start = middle;
                arc_area1 = test.getArcArea(r, start);
                flag1 = (area - arc_area1) / arc_area1;
            } else {
                if ((ratio - flag) < 0.0001) {
                    break;
                }
                end = middle;
                arc_area2 = test.getArcArea(r, end);
                flag2 = (area - arc_area2) / arc_area2;
            }
            middle = (start + end) / 2;
        }
        // 输出结果
        System.out.println("arc_area:"+test.getArcArea(r, middle));
        System.out.println("middle:"+middle);
        System.out.println("arc:"+2 * test.getHalfArc(r, middle));
    }
}
