import React, { useEffect, useState } from "react";
import { AgGridReact } from 'ag-grid-react';

import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-material.css';

export default function TrainList() {
    const [trains, setTrains] = useState([]);
    const [gridApi, setGridApi] = useState(null);

    const onGridReady = (params) => {
        setGridApi(params.api);
    }

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

    const columns = [
        {
            headerName: "Name",
            field: "name",
            sortable: true,
            filter: true,
            cellStyle: {
                textAlign: "left"
            },
            width: 300
        },
        {
            headerName: "Destination",
            field: "destination",
            sortable: true,
            filter: true,
            cellStyle: {
                textAlign: "left"
            }
        },
        {
            headerName: "Speed",
            field: "speed",
            sortable: true,
            filter: true,
            cellStyle: {
                textAlign: "left"
            }
        },
        {
            headerName: "Coordinates",
            field: "location.coordinates",
            sortable: true,
            filter: true,
            cellStyle: {
                textAlign: "left"
            }
        }
    ]

    return (
        <div>
            <div className="ag-theme-material" style={{height: 600, width: "50%", marginLeft: "auto", marginRight: "auto"}}>
                <AgGridReact
                    columnDefs={columns}
                    rowData={trains}
                    pagination={true}
                    paginationPageSize={10}
                    suppressCellFocus={false}
                    onGridReady={onGridReady}
                />
            </div>
        </div>
    );
}