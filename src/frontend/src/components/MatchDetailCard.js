import { React } from 'react'
import {Link} from "react-router-dom";
import './MatchDetailCard.scss';

export const MatchDetailCard = ({teamName,match}) => {
    if(!match) return null;
    const otherTeam =match.homeTeam === teamName ? match.awayTeam : match.homeTeam;
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWin = teamName=== match.winner;
    return (
        <div className={isMatchWin ? 'MatchDetailCard won-card' : 'MatchDetailCard loss-card'}>
            <span className="vs">vs </span>
            <h1><Link to = {otherTeamRoute}> {otherTeam} </Link></h1>
            <h2 className="match-date"> {match.date}</h2>
            <h2 className="match-winner">Match winner:  {match.winner} </h2>
                <h3 className="match-result">Score: {match.homeGoal} - {match.awayGoal}</h3>
        </div>
    );
}