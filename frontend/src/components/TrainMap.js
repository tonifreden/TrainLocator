import React, { useEffect, useState } from "react";
import { Map, Marker } from "pigeon-maps";

export default function TrainMap() {
    const [trains, setTrains] = useState([]);

    useEffect(() => {
        fetchTrains();
        const interval = setInterval(() => {
            fetchTrains()
        }, 10000);
        return () => clearInterval(interval);
    }, []);

    const fetchTrains = () => {
        fetch("http://localhost:8080/api/trains")
        .then(response => response.json())
        .then(data => setTrains(data))
        .catch(err => console.error(err));
    }

    return (
        <div>
            <Map height={600} width="50%" defaultCenter={[65.22728, 27.72846]} defaultZoom={5}>
                {trains.map((train, id) => {
                    return <Marker key={id} width={50} anchor={train.location.coordinates} />
                })}
            </Map>
        </div>
    );
}