import {Pipe} from "@angular/core";

@Pipe({
    name: "statusRender",
})

export class StatusRender {
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
                src = '/assets/img/green.png';
            } else if(src == '1') {
                src = '/assets/img/red.png';
            } else if(src == '2') {
                src = '/assets/img/yellow.png';
            } else if(src == '3') {
                src = '/assets/img/yellow.png';
            }
        }
        return src;
    }
}
