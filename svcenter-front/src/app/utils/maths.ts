export class Maths {

    // 强转一个数值类型为int
    public static numberToInt(num: number): number {
        return num & 0xFFFFFFFF;
    }

    // 强转一个数值类型为byte
    public static numberToByte(num: number): number {
        return num & 0xFF;
    }

    // 强转一个数值类型为short
    public static numberToShort(num: number): number {
        return num & 0xFFFF;
    }

    // 强转一个数值类型为long
    public static numberToLong(num: number): number {
        return num & 0xFFFFFFFFFFFFFFFF;
    }

    // 随机一个区间数
    public static random(start: number, end: number): number {
        return Math.random() * (end - start) + start;
    }

    // 截去小数位
    public static parseInt(num: number): number {
        if (num > 0) {
            return num >> 0;
        } else {
            return Math.ceil(num);
        }
    }

    // 两点间的距离
    public static getDistance(x1: number, y1: number, x2: number, y2: number): number {
        return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
    }

    // 两点间的弧度：x正方形为0，顺时针为正
    public static getLineRadians(x1: number, y1: number, x2: number, y2: number): number {
        return Math.atan2(y2 - y1, x2 - x1);
    }

    public static isASCIIByte(a: number): boolean {
        return 0x00 <= a && a <= 0x7F;
    }

    public static inRange(a: number, min: number, max: number): boolean {
        return min <= a && a <= max;
    }
}
