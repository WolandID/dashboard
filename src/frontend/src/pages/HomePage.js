import { React, useEffect, useState } from 'react'
import {TeamTile} from "../components/TeamTile";

import './HomePage.scss'

export const HomePage = () => {
    const [teams,setTeams] = useState([]);


    useEffect(
        ()=>{
            const fetchAllTeams = async() =>{
               const response = await fetch(`${process.env.REACT_APP_API_URL}/team`);
               const data = await response.json();
               setTeams(data);
        };
            fetchAllTeams();
        }, []
    );
////    if(!team || !teamName){
////        return <h1>Team not found</h1>
//    }


    return (
        <div className="HomePage">
            <div className="header-section">
                <h1>English Premier League Dashboard(1993-2019)</h1>
            </div>
             <div className="team-tile">
                {teams.map(team=><TeamTile key={team.id} teamName={team.teamName}/> )}
             </div>
        </div>
    );

}
