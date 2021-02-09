
import { Pipe, PipeTransform } from '@angular/core';

import * as Moment from 'moment'

@Pipe({ name: 'temporalPresent' })
export default class TemporalPresentPipe implements PipeTransform {

    transform(value: number): string {
        if (!value) {
            return "N/D";
        }

        let temporal = Moment.duration(value);

        return " " + temporal.days() + "d "  + temporal.hours() + "h:" + temporal.minutes() + "m:" + temporal.seconds()+"s";
    }
}