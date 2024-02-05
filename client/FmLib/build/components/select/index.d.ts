import React, { FC } from 'react';
import { SelectProps as MuiSelectProps, MenuItemProps } from '@mui/material';
type SelectProps = {
    children: React.ReactNode | React.ReactNode[];
    helperText?: string;
} & MuiSelectProps;
declare const Select: FC<SelectProps>;
type SelectMenuItemProps = {
    children: React.ReactNode | React.ReactNode[];
} & MenuItemProps;
declare const SelectMenuItem: FC<SelectMenuItemProps>;
export { Select, SelectMenuItem };
