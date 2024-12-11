import React, { useState } from 'react';
import ConfigurationForm from './components/ConfigurationForm';
import ControlPanel from './components/ControlPanel';
import LogDisplay from './components/LogDisplay';
import TicketStatus from './components/TicketStatus';
import './styles.css';

// Main application
const App = () => {
  // Tracking if system is configured
  const [isConfigured, setIsConfigured] = useState(false);

  // Calling for ticket configuration
  const handleConfigured = () => setIsConfigured(true);

  return (
    <div className="app">
      <h1>Real-Time Event Ticketing System</h1>
      {!isConfigured ? (
        // Show form if not configured
        <ConfigurationForm onConfigured={handleConfigured} />
      ) : (
        <>
          <ControlPanel />
          <TicketStatus />
          <LogDisplay />
        </>
      )}
    </div>
  );
};

export default App;
