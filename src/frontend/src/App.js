import './App.scss';
import {Routes,Route} from 'react-router-dom';
import {TeamPage} from "./pages/TeamPage";
import {MatchPage} from "./pages/MatchPage";

function App() {
  return (
    <div className="App">
        <Routes>
            <Route path="/teams/:teamName/matches/:year"
                   element= {<MatchPage />}/>
            <Route path="/teams/:teamName"
                       element= {<TeamPage />}/>

        </Routes>
    </div>
  );
}

export default App;
