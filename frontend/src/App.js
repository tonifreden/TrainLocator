import './App.css';
import TrainList from './components/TrainList';
import { Tab, Tabs } from "@mui/material";
import { useState } from 'react';
import TrainMap from './components/TrainMap';

function App() {
  const [tab, setTab] = useState("trainlist");

  const tabChange = (event, value) => {
    setTab(value);
  }

  return (
    <div className="App">
      <header className="App-header">
        <Tabs value={tab} onChange={tabChange} textColor="inherit" TabIndicatorProps={{style: {background: "white"}}} centered sx={{marginBottom: 5}}>
          <Tab value="trainlist" label="Train list" />
          <Tab value="trainmap" label="Train map" />
        </Tabs>
        {tab === "trainlist" && <TrainList />}
        {tab === "trainmap" && <TrainMap />}
      </header>
    </div>
  );
}

export default App;
