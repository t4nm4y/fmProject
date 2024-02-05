import React, { FC, MouseEventHandler } from 'react';
type BackdropProps = {
    children: React.ReactNode | React.ReactNode[];
    open: boolean;
    handleClose: MouseEventHandler;
    zIndex?: number;
};
declare const Backdrop: FC<BackdropProps>;
export { Backdrop };
