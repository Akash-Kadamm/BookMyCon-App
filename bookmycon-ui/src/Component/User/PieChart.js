// import React, { useState, useEffect } from 'react';
// import { Chart } from 'react-google-charts';
// import "../../../src/App.css";
// import TableComponent from './ViewChartTable';
// import Button from './ViewChartButton';
 
// const PieChart = () => {
//   const [totalAuditoriumCount, setTotalAuditoriumCount] = useState(0);
//   const [bookedAuditoriumCount, setBookedAuditoriumCount] = useState(0);
 
//   useEffect(() => {
//     const fetchData = async () => {
//       try {
//         const totalAuditoriumResponse = await fetch('http://localhost:8080/admin/totalAuditoriumCount');
//         const totalAuditoriumData = await totalAuditoriumResponse.json();
//         console.log('Total Auditorium Response:', totalAuditoriumData);
 
//         const bookedAuditoriumResponse = await fetch('http://localhost:8080/admin/booked/count');
//         const bookedAuditoriumData = await bookedAuditoriumResponse.json();
//         console.log('Booked Auditorium Response:', bookedAuditoriumData);
 
//         setTotalAuditoriumCount(totalAuditoriumData); // Update state with count
//         setBookedAuditoriumCount(bookedAuditoriumData); // Update state with count
//       } catch (error) {
//         console.error('Error fetching data:', error);
//       }
//     };
 
//     fetchData();
//   }, []);
 
//   const processDataForPieChart = (totalAuditoriumCount, bookedAuditoriumCount) => {
//     const unbookedAuditoriumCount = totalAuditoriumCount - bookedAuditoriumCount;
//     return [
//       ['Status', 'Count'],
//       ['Booked', bookedAuditoriumCount],
//       ['Unbooked', unbookedAuditoriumCount]
//     ];
//   };
 
//   const data = processDataForPieChart(totalAuditoriumCount, bookedAuditoriumCount);
 
//   return (
//     <div className="Chart">
//       <Chart
//         width={'100%'}
//         height={'400px'}
//         chartType="PieChart"
//         loader={<div>Loading Chart</div>}
//         data={data}
//         options={{
//           title: 'Auditorium Booking Status',
//         }}
//         rootProps={{ 'data-testid': '1' }}
//       />
     
 
// <TableComponent totalAuditoriumCount={totalAuditoriumCount} bookedAuditoriumCount={bookedAuditoriumCount} />
 
// <Button/>
//     </div>
//   );
// };
 
// export default PieChart;