import React from 'react';
import { ScheduleComponent, Day, Week, WorkWeek, Month, Agenda, Inject ,ViewDirective,ViewsDirective} from '@syncfusion/ej2-react-schedule';
import {DataManager,WebApiAdaptor} from '@syncfusion/ej2-data'
function Calendar() {
   const data = [
    {
        Id: 2,
        Subject: 'Meeting',
        StartTime: new Date(2023, 1, 22, 10, 0),
        EndTime: new Date(2023, 1, 22, 12, 30),
        IsAllDay: false,
        Status: 'Completed',
        Priority: 'High'
    },

    {
      Id: 3,
      Subject: 'Conference',
      StartTime: new Date(2023, 1, 23, 9, 0),
      EndTime: new Date(2023, 1, 23, 11, 30),
      IsAllDay: false,
      Status: 'Completed',
      Priority: 'High'
  },
];

  return (
    <div className="App">
     <ScheduleComponent eventSettings={{
      dataSource:data,
      fields: {
        id: 'Id',
        subject: { name: 'Subject' },
        isAllDay: { name: 'IsAllDay' },
        startTime: { name: 'StartTime' },
        endTime: { name: 'EndTime' }
    }
     }}>
      <ViewsDirective>
            <ViewDirective option='Day' />
            <ViewDirective option='Week' />
            <ViewDirective option='WorkWeek' />
            <ViewDirective option='Month' />
            <ViewDirective option='Agenda' />
          </ViewsDirective>
      <Inject services={[Day,Week,WorkWeek,Month,Agenda]}/>
     </ScheduleComponent>
    </div>
  );
}

export default Calendar;
