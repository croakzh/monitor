import { Pipe } from "@angular/core";

@Pipe({
    name: "dic2Array",
})

export class Dic2Array {
    public transform(dics: object): any[] {
        const result = [];

        for (const key in dics) {
            if ( 1 === 1) {// for in必须要个if（不知道为啥）
                result.push({key, value: dics[key]});
            }
        }

        return result;
    }
}
