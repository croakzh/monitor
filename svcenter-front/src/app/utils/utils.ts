import {NzTreeNode} from "ng-zorro-antd";

/**
 * 工具
 */
export class Utils {

    // 将列表式数据构建成树形数据
    public static buildTreeData(datas, parentField, keyField, titleField, callback?): NzTreeNode[] {
        const target = [];
        for (const ele of datas) {
            const item = {
                title: ele[titleField],
                key: ele[keyField] + "",
                data: ele,
                children: [],
                isLeaf: true,
            };
            target.push(item);
        }

        let i = 0;
        while (target.length > i) {
            if (parentField && target[i].data[parentField] != null && target[i].data[parentField] !== "") {
                this.insertChildAtId(target, target[i].data[parentField], target[i], keyField, callback);
                target.splice(i, 1);
            } else {
                i++;
            }
        }

        const result: NzTreeNode[] = [];
        target.forEach((node) => {
            if (callback) {
                callback(node, node.data);
            }
            result.push(new NzTreeNode(node));
        });
        return result;
    }

    public static insertChildAtId(array, strId, objChild, keyField, callback) {
        let found = false;
        for (const ele of array) {
            if (ele.data[keyField] === strId) {
                if (callback) {
                    callback(objChild, ele);
                }
                ele.children.push(objChild);
                ele.isLeaf = false;
                return true;
            } else if (ele.children) {
                found = this.insertChildAtId(ele.children, strId, objChild, keyField, callback);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    // 获取所有勾选了节点（仅叶子节点）
    public static getCheckedNodes(nodes): any[] {
        let result = [];
        for (const node of nodes) {
            if (node.isChecked || node.isHalfChecked) {
                result.push(node);
            }

            if (!node.isLeaf && node.children.length > 0) {
                result = result.concat(this.getCheckedNodes(node.children));
            }
        }
        return result;
    }

    public static urlSearch(url): any {
        let name;
        let value;
        let num = url.indexOf("?");
        url = url.substr(num + 1); // 取得所有参数   stringvar.substr(start [, length ]

        const arr = url.split("&"); // 各个参数放到数组里
        const result = {};
        for (const item of arr) {
            num = item.indexOf("=");
            if (num > 0) {
                name = item.substring(0, num);
                value = item.substr(num + 1);
                result[name] = value;
            }
        }
        return result;
    }

    // 手机号只显示前后各三位
    public static displayTel(tel): string {
        let displayTel = "";
        if (tel) {
            if (tel.length === 11) {
                displayTel = tel.substr(0, 3) + "*****" + tel.substr(8, 3);
            } else {
                displayTel = tel;
            }
        }
        return displayTel;
    }

    // 邮箱只显示@前面的部分前后各两位
    public static displayEmail(email): string {
        let displayEmail = "";
        let tempStr: string[] = [];
        if (email) {
            if (email.indexOf("@") > 0) {
                tempStr = email.split("@");
                const preEmaillength = tempStr[0].length;
                if (preEmaillength > 4) {
                    let hideStar = "";
                    for (let i = 0; i < (preEmaillength - 4); i++) {
                        hideStar += "*";
                    }
                    displayEmail = tempStr[0].substr(0, 2) + hideStar + tempStr[0].substr(preEmaillength - 2, 2) + "@" + tempStr[1];
                } else {
                    displayEmail = email;
                }
            } else {
                displayEmail = email;
            }
        }
        return displayEmail;
    }

    /**
     * 当前时间戳
     * @returns {number}
     */
    public static currentTimeMillis(): number {
        return new Date().getTime();
    }

    /***
     * 当前的秒数
     * @returns {number}
     */
    public static currentTimeSeconds(): number {
        return this.currentTimeMillis() / 1000;
    }

    // 构造函数
    constructor() {
        //
    }

}
