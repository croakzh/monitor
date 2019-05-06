import {Pipe} from "@angular/core";

@Pipe({
    name: "setRender",
})

export class SetRender {
    public transform(fieldName: string, column: any, data: any): string {
        if (column.render) {
            return column.render(data);
        }
        let src = data[fieldName];
        if (fieldName == 'status') {
            if (src == '0') {
                src = '/assets/img/green.png';
            } else if (src == '1') {
                src = '/assets/img/red.png';
            }
        } else if(fieldName == 'appstatus') {
            if(src == '0') {
                src = '停止';
            } else if(src == '1') {
                src = '启动';
            } else if(src == '2') {
                src = '';
                // src = '/assets/img/pending.png';
            } else if(src == '3') {
                src = '';
                // src = '/assets/img/pending.png';
            }
        }
        return src;
    }
}
