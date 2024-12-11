import React, { useEffect, useState } from 'react';
import { getLogs, deleteLogs } from '../api'; 

const LogDisplay = () => {
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    const fetchLogs = async () => {
      const data = await getLogs();
      setLogs(data);
    };
  
    fetchLogs();
    const interval = setInterval(fetchLogs, 5000); 
  
    return () => clearInterval(interval); 
  }, []);  

 
  const handleDeleteLogs = async () => {
    try {
      await deleteLogs(); 
      setLogs([]); 
      alert('All logs have been deleted successfully.');
    } catch (error) {
      alert('Failed to delete logs.');
    }
  };

  return (
    <div className="log-display">
      <h3>System Logs</h3>
      <button onClick={handleDeleteLogs} className="delete-logs-btn">
        Delete All Logs
      </button>
      <ul>
        {logs.map((log) => (
          <li key={log.id}>
            <strong>{new Date(log.timestamp).toLocaleString()}</strong>: {log.message}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LogDisplay;
