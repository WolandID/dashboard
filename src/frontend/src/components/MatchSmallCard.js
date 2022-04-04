import { React } from 'react'
import {matches} from "@testing-library/jest-dom/dist/utils";

export const MatchSmallCard = ({match,teamName}) => {


    return (
        <div className="MatchSmallCard">
           <p>{match.homeTeam} vs {match.awayTeam}</p>
        </div>
    );
}