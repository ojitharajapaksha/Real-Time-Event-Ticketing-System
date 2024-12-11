import React, { useState } from 'react';
import { startSystem, stopSystem } from '../api';


// Control panel for controllig the system(start/stop)
const ControlPanel = () => {
  // State for button loading
  const [loading, setLoading] = useState(false);

  // Handler for starting the system
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

  // Handler for stopping the system
  const handleStop = async () => {
    setLoading(true);
    try {
      // Call API to start the system
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
        {/*Start and Stop buttons*/}
          <button onClick={handleStart} className="start-btn">Start System</button>
          <button onClick={handleStop} className="stop-btn">Stop System</button>
        </>
      )}
    </div>
  );
};

export default ControlPanel;
