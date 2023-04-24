import React, { useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Select from "react-select";
import "./PageByComp.css";
import { toast } from "react-toastify";
import axios from "axios";
import { useNavigate } from "react-router";

export default function PageByComp() {
  const [value, setValue] = useState(null);
  const navigate = useNavigate();

  const options = [
    { value: "company 1", label: "cybage" },
    { value: "company 2", label: "mastercard" },
    { value: "company 3", label: "apple" },
    { value: "company 4", label: "accenture" },
  ];

  const handleSubmit = () => {
    const selectedoption = options.find(
      (option) => option.value === value.value
    );
    console.log(selectedoption.label);
    axios
      .get("http://localhost:8080/users/migrateUsers/" + selectedoption.label, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
        toast.info(response.data);
        navigate("/");
      });
  };

  return (
    <div className="PagesByComp">
      <div className="text-container">
        <Grid item xs={4}>
          <Card
            sx={{
              minWidth: 10 + "%",
              height: 250,
              backgroundColor: " rgb(190, 233, 219);",
            }}
          >
            <CardContent>
              <Typography
                sx={{ fontSize: 22, textAlign: "center" }}
                color="text.secondary"
                gutterBottom
              >
                Data migration using Company Name involves moving data from one
                system or storage location to another. When using the company
                name as a data migration method, data is organized and
                transferred according to the company's structure and hierarchy.
                This ensures that data is migrated to the appropriate location
                and accessible by the right people.This data migration is
                GDPR(General Data Protection Regulation ) compatible.
              </Typography>
            </CardContent>
            <CardActions></CardActions>
          </Card>
        </Grid>

        <div className="corosel">
          <div classsname="dropButton" style={{ margin: 20, width: 400 }}>
            <Select
              options={options}
              defaultValue={value}
              placeholder="Select Companey Name  "
              onChange={setValue}
              isSearchable
            ></Select>
            {/* </div> */}

            <Button>
              <input
                type="submit"
                id="submit-bt2"
                value="Submit"
                onClick={handleSubmit}
              />
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}
