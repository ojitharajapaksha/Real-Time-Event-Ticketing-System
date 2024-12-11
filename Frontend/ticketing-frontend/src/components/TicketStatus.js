import React, { useEffect, useState } from 'react';
import axios from 'axios';

const TicketStatus = () => {
  const [ticketCount, setTicketCount] = useState(0);

  useEffect(() => {
    const fetchTicketCount = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/ticket-count');
        setTicketCount(response.data); 
      } catch (err) {
        console.error('Failed to fetch ticket count:', err);
      }
    };
  
    fetchTicketCount();
    const interval = setInterval(fetchTicketCount, 3000); 
  
    return () => clearInterval(interval); 
  }, []);  

  return (
    <div className="ticket-status">
      <h3>Real-Time Ticket Status</h3>
      <p>Available Tickets: {ticketCount}</p>
    </div>
  );
};

export default TicketStatus;
