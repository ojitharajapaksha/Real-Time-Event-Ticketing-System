import React, { useState } from 'react';
import { startSystem, stopSystem } from '../api';

const ControlPanel = () => {
  const [loading, setLoading] = useState(false);

  const handleStart = async () => {
    setLoading(true);
    try {
      await startSystem();
      alert('System started.');
    } catch (error) {
      alert('Failed to start the system.');
    } finally {
      setLoading(false);
    }
  };

  const handleStop = async () => {
    setLoading(true);
    try {
      await stopSystem();
      alert('System stopped.');
    } catch (error) {
      alert('Failed to stop the system.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="control-panel">
      {loading ? (
        <p>Loading...</p>
      ) : (
        <>
          <button onClick={handleStart} className="start-btn">Start System</button>
          <button onClick={handleStop} className="stop-btn">Stop System</button>
        </>
      )}
    </div>
  );
};

export default ControlPanel;
