import {Pipe} from "@angular/core";

@Pipe({
    name: "fieldRender",
})

export class FieldRender {
    public transform(fieldName: string, column: any, data: any): string {
        if (column.render) {
            return column.render(data);
        }
        let name = data[fieldName];
        if (fieldName == 'status') {
            if (name == '0') {
                name = '在线';
            } else if (name == '1') {
                name = '离线';
            }
        } else if(fieldName == 'appstatus') {
            if(name == '0') {
                name = '运行中';
            } else if(name == '1') {
                name = '未启动';
            } else if(name == '2') {
                name = '启动中';
            } else if(name == '3') {
                name = '脚本异常';
            }
        }
        return name;
    }
}
