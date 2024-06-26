// import React, { useState, useEffect } from 'react';
// import { Table, TableContainer, TableHead, TableBody, TableRow, TableCell, Paper } from '@mui/material';

// const BackendApiTable = () => {
//   const [data, setData] = useState([]);

//   useEffect(() => {
//     fetch('http://localhost:8080/bookings')
//       .then(response => response.json())
//       .then(data => setData(data))
//       .catch(error => console.error('Error fetching data:', error));
//   }, []);

//   return (
//     <TableContainer component={Paper}>
//       <Table>
//         <TableHead>
//           <TableRow>
//             <TableCell>User Name</TableCell>
//             <TableCell>User Email</TableCell>
//             <TableCell>Auditorium Name</TableCell>
//             <TableCell>Booking Date From</TableCell>
//             <TableCell>Booking Date To</TableCell>
//             <TableCell>Booking Time From</TableCell>
//             <TableCell>Booking Time To</TableCell>
//             <TableCell>Booking Agenda</TableCell>
//           </TableRow>
//         </TableHead>
//         <TableBody>
//           {data.map(booking => (
//             <TableRow key={booking.bookingId}>
//               <TableCell>{booking.userId.userName}</TableCell>
//               <TableCell>{booking.userId.userEmail}</TableCell>
//               <TableCell>{booking.aduitoriamId.auditoriumName}</TableCell>
//               <TableCell>{new Date(...booking.bookingDateFrom).toLocaleDateString()}</TableCell>
//               <TableCell>{new Date(...booking.bookingDateTo).toLocaleDateString()}</TableCell>
//               <TableCell>{booking.bookingTimeFrom.join(':')}</TableCell>
//               <TableCell>{booking.bookingTimeTO.join(':')}</TableCell>
//               <TableCell>{booking.bookingAgenda}</TableCell>
//             </TableRow>
//           ))}
//         </TableBody>
//       </Table>
//     </TableContainer>
//   );
// };

// export default BackendApiTable;
