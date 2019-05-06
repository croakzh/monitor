import {Injectable} from "@angular/core";
import {ValidResult} from "./models/validResult";
import {ValidField} from "./models/validField";
import * as $_ from "underscore";
import {ValidType} from "./models/valid-type";

@Injectable()
export class ValidService {

    // 是否是function
    public static isFunction(obj: any): boolean {
        return typeof(obj) === "function";
    }

    // 是否是boolean
    public static isBoolean(obj: any): boolean {
        return typeof(obj) === "boolean";
    }

    // 是否是string
    public static isString(obj: any): boolean {
        return typeof(obj) === "string";
    }

    // 验证是否为空
    public static isEmpty(chkValue): boolean {
        return chkValue !== 0 && $_.isEmpty(chkValue);
    }

    // 验证数字跟英文字母
    public static isNumberAndLetter(chkValue: string): boolean {
        return /^[0-9a-zA-Z]+$/.test(chkValue) ? true : false;
    }

    // 验证是否为数字
    public static isNumber(chkValue: any): boolean {
        // return $_.isNumber(chkValue);
        return /^[0-9]+.?[0-9]*$/.test(chkValue) ? true : false;
    }

    // 验证是否同时包含数字与大小写字母(主要用于密码检查)
    public static isContainNumCapitalLowerLetter(chkValue: string): boolean {
        return /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/.test(chkValue) ? true : false;
    }

    // 验证是否为电话号码
    public static isTel(chkValue: string): boolean {
        return /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(chkValue) ? true : false;
    }

    // 验证是否为日期
    public static isDate(chkValue: string): boolean {
        return $_.isDate(chkValue);
    }

    // 验证是否为邮件格式
    public static isEmail(chkValue: string): boolean {
        return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(chkValue) ? true : false;
    }

    // 验证是否超过最大长度
    public static overMaxLength(chkValue: string, maxLength: number) {
        if (!chkValue) {
            return null;
        }
        if (chkValue.length > maxLength) {
            return true;
        }
        return false;
    }

    protected validResults: ValidResult[];

    // 根据需校验的类型 对字段进行校验 返回校验结果
    public check(field: ValidField): ValidResult[] {
        this.validResults = [];
        for (const type of field.types) {
            // if (type instanceof Array) {
            //     this.validResults.push(this.getCheckResult(field, type[0], type[1]));
            // } else {
            this.validResults.push(this.getCheckResult(field, type));
            // }
        }
        return this.validResults;
    }

    protected getCheckResult(field: ValidField, validType: any): ValidResult {
        let type = null;
        if (validType instanceof Array) {
            type = validType[0];
        } else {
            type = validType;
        }
        switch (type) {
            case ValidType.REQUIRED:
                return {
                    result: !ValidService.isEmpty(field.value),
                    message: "请输入" + field.name,
                };
            case ValidType.NUMBER :
                return {
                    result: ValidService.isNumber(field.value),
                    message: field.name + "只能输入数字",
                };
            case ValidType.NUMBERANDLETTER :
                return {
                    result: ValidService.isNumberAndLetter(field.value),
                    message: field.name + "只能输入英数字",
                };
            case ValidType.DATE :
                return {
                    result: ValidService.isDate(field.value),
                    message: "输入的" + field.name + "非日期格式",
                };
            case ValidType.TEL :
                return {
                    result: ValidService.isTel(field.value),
                    message: "输入的" + field.name + "非电话号码",
                };
            case ValidType.EMAIL :
                return {
                    result: ValidService.isEmail(field.value),
                    message: "输入的" + field.name + "非邮件格式",
                };
            case ValidType.MAXLENGTH:
                return {
                    result: !ValidService.overMaxLength(field.value, validType[1]),
                    message: field.name + "已超出最大长度限制",
                };
            case ValidType.CUSTOM:
                if (typeof validType[1] === "function") {
                    return {
                        result: validType[1](field.value),
                        message: validType[2],
                    };
                } else {
                    return new ValidResult();
                }
            default:
                return new ValidResult();
        }
    }
}
