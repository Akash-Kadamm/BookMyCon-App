
import React, { useEffect, useState } from 'react';
import { PieChart, Pie, Legend, Tooltip, Cell,ResponsiveContainer } from 'recharts';
import { CircularProgress, Dialog, DialogTitle, DialogContent, Typography } from '@material-ui/core';
import '../../css/Piechart.css'

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

const PieChartFloor = ({ open, handleClose }) => {
  const [data, setData] = useState([]);

  useEffect(() => {
   
    fetch('http://localhost:8080/api/piechart/all')
      .then(response => response.json())
      .then(data => setData(data))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>
        <Typography variant="h6" align="center">
          Capacity Distribution
        </Typography>
      </DialogTitle>
      <DialogContent>
        {data.length === 0 ? (
          <CircularProgress />
        ) : (
            <PieChart width={700} height={600} className='Piechart'>
            <Pie
              data={data}
              cx={250}
              cy={200}
              innerRadius={60}
              outerRadius={80}
              fill="#8884d8"
              dataKey="capacity"
              label
              
            >
              {data.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Legend verticalAlign="bottom" height={120} textAnchor='center' marginLeft={-60}/>
            <Tooltip />
          </PieChart>
        )}
      </DialogContent>
    </Dialog>
  );
};

export default PieChartFloor;
