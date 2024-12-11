import React, { useEffect, useState } from 'react';
import { getLogs, deleteLogs } from '../api'; 

// Displaying the system logs
const LogDisplay = () => {
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    const fetchLogs = async () => {
      // Fetching logs from API
      const data = await getLogs();
      setLogs(data);
    };
  
    fetchLogs();
    // Poll logs every  seconds
    const interval = setInterval(fetchLogs, 5000); 
  
    // Cleanup interval on unmount
    return () => clearInterval(interval); 
  }, []);  

 // Handler for deleting logs
  const handleDeleteLogs = async () => {
    try {
      await deleteLogs(); 
      // Call API to delete logs
      setLogs([]); 
      // Clear logs state
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
