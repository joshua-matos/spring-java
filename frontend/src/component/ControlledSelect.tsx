import { Controller } from "react-hook-form";
import FormControl from "@mui/material/FormControl";
import { MenuItem, Select, Typography } from "@mui/material";
import React from "react";

type ControlledSelectProps = {
  name: string;
  label: string;
  control: any;
  errors: any;
  selection: Array<string>;
  showLabel?: boolean;
  defaultValue?: string | number;
};

const ControlledSelect = ({
  name,
  label,
  control,
  errors,
  selection,
  showLabel = false,
  defaultValue,
}: ControlledSelectProps) => {
  return (
    <Controller
      name={name}
      control={control}
      defaultValue={defaultValue ?? ""}
      render={({ field }) => {
        return (
          <FormControl fullWidth>
            {showLabel && (
              <Typography
                variant="subtitle1"
                sx={{
                  width: "100%",
                  display: "flex",
                  justifyContent: "flex-end",
                  color: "text.secondary",
                  fontSize: "0.6rem",
                }}
              >
                {label}
              </Typography>
            )}
            <Select
              variant="standard"
              labelId={name}
              id={name}
              {...field}
              error={!!errors[field.name]}
            >
              {selection.map((item, index) => {
                const value = item === "Yes";
                return (
                  <MenuItem key={index} value={value as any}>
                    <Typography variant="body2" color="text.secondary">
                      {item}
                    </Typography>
                  </MenuItem>
                );
              })}
            </Select>
          </FormControl>
        );
      }}
    />
  );
};

export default ControlledSelect;
