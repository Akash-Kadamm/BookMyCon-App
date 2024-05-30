import React from 'react';
 
const TableComponent = ({ totalAuditoriumCount, bookedAuditoriumCount }) => {
    // Calculate unbooked count
    const unbookedAuditoriumCount = totalAuditoriumCount - bookedAuditoriumCount;
 
    const styles = `
    .custom-table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px; /* Add spacing from the chart */
    }
 
    .custom-table th, .custom-table td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }
 
    .custom-table th {
      background-color: #f2f2f2;
    }
 
    .custom-table tbody tr:hover { /* Hover effect */
      background-color: #f5f5f5;
    }
  `;
    <h2>Count Table</h2>
    return (
        <div>
            <h2>Auditorium Information</h2>
            <style>{styles}</style>
 
            <table className="custom-table">
 
                <thead>
                    <tr>
 
                        <th>Total Count</th>
                        <th>Booked</th>
                        <th>Unbooked</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{totalAuditoriumCount}</td>
                        <td>{bookedAuditoriumCount}</td>
                        <td>{unbookedAuditoriumCount}</td>
                    </tr>
                    <tr>
                        <td>{totalAuditoriumCount}</td>
                        <td>{bookedAuditoriumCount}</td>
                        <td>{unbookedAuditoriumCount}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}
 
export default TableComponent;