import React, { useState } from 'react';
import ConfigurationForm from './components/ConfigurationForm';
import ControlPanel from './components/ControlPanel';
import LogDisplay from './components/LogDisplay';
import TicketStatus from './components/TicketStatus';
import './style.css';

const App = () => {
  const [isConfigured, setIsConfigured] = useState(false);

  const handleConfigured = () => setIsConfigured(true);

  return (
    <div className="app">
      <h1>Real-Time Event Ticketing System</h1>
      {!isConfigured ? (
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
