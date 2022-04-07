import { React } from 'react'
import {Link} from "react-router-dom";
import './MatchSmallCard.scss';

export const MatchSmallCard = ({match,teamName}) => {
    if(!match) return null;
    const otherTeam =match.homeTeam === teamName ? match.awayTeam : match.homeTeam;
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWin = teamName=== match.winner;


    return (
        <div className={isMatchWin ? 'MatchSmallCard won-card' : 'MatchSmallCard loss-card'}>
            <span className="vs">vs </span>
               <Link to = {otherTeamRoute}> {otherTeam} </Link>
            <p>Match winner:  {match.winner} </p>
            <p>Score: {match.homeGoal} - {match.awayGoal}</p>

        </div>
    );
}